package uni.fmi.st.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uni.fmi.st.models.User;
import uni.fmi.st.repos.UserRepository;

@Service
public class ApplicationDetailsService implements UserDetailsService {

	private UserRepository userRepo;

	@Autowired
	public ApplicationDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		final User user = userRepo.findByEmail(email);
		if (null == user) {
			throw new UsernameNotFoundException("User with email=''" + email + "'' does not exist!");
		}
		return new UserPrinciple(user);
	}

}
