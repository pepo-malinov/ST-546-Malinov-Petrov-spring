package uni.fmi.st.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import uni.fmi.st.models.User;
import uni.fmi.st.repos.UserRepository;

@RestController
public class UserLoginRest {

	private UserRepository repository;
	private UserDetailsService userDetailsService;

	@Autowired
	public UserLoginRest(final UserRepository repository,
						UserDetailsService userDetailsService ) {
		this.repository = repository;
		this.userDetailsService = userDetailsService;
	}

	@PostMapping(value = "/login")
	public String login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password,
			HttpSession session) {
		final User currentUser = repository
							.findByEmailAndPassword(email, password);
		if (null != currentUser) {
			session.setAttribute("currentUser", currentUser);
			UserDetails securityUser = userDetailsService
					.loadUserByUsername(currentUser.getUsername());
			if(null == securityUser) {
				securityUser = userDetailsService
						.loadUserByUsername("user");
			}
			Authentication authentication = new 
					UsernamePasswordAuthenticationToken(securityUser.getUsername(),
							securityUser.getPassword());
			SecurityContextHolder.getContext()
							.setAuthentication(authentication);
			ServletRequestAttributes attr = (ServletRequestAttributes)
					RequestContextHolder.currentRequestAttributes();
			HttpSession httpSession = attr.getRequest().getSession(true); // true == allow create
			httpSession.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

		}
		return "profile.html";
	}

	@PostMapping(value = "/register")
	public User register(@RequestParam(name = "email") String email, @RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {
		final User newUser = new User(username, password, email);
		return repository.saveAndFlush(newUser);
	}

}
