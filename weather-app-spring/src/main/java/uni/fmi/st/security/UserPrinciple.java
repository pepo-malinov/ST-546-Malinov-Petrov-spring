package uni.fmi.st.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import uni.fmi.st.models.Role;
import uni.fmi.st.models.User;

public class UserPrinciple implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;
	private Collection<GrantedAuthority> authorities = new ArrayList<>();

	public UserPrinciple(final User user) {
		this.user = user;
		final Set<Role> roles = user.getRoles();
		if (null != roles && !roles.isEmpty()) {
			roles.forEach(role -> 
			authorities.add(new SimpleGrantedAuthority(role.getCode())));
		//
			/*for(Role role:roles) {
				authorities.add(new SimpleGrantedAuthority(role.getCode()));
			}*/
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
