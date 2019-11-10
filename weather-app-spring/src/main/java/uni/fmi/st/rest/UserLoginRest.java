package uni.fmi.st.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uni.fmi.st.models.User;
import uni.fmi.st.repos.UserRepository;

@RestController
public class UserLoginRest {

	private UserRepository repository;

	@Autowired
	public UserLoginRest(final UserRepository repository) {
		this.repository = repository;
	}

	@PostMapping(value = "/login")
	public User login(@RequestParam(name = "email") String email,
				@RequestParam(name = "password") String password) {
		return repository.findByEmailAndPassword(email, password);
	}

	@PostMapping(value = "/register")
	public User register(@RequestParam(name = "email") String email,
			@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {
		final User newUser = new User(username, password, email);
		return repository.saveAndFlush(newUser);
	}

}
