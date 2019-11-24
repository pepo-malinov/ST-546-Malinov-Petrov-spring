/**
 * 
 */
package uni.fmi.st.security;

import org.springframework.boot.actuate.autoconfigure.security
											.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Base spring security config class
 * @author fmi
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/home.html").hasRole("ADMIN")
		.regexMatchers(HttpMethod.POST, "/removeMyPosts")
		.hasAnyRole("ADMIN", "USER")
		.regexMatchers(HttpMethod.POST, "/register")
		.hasAnyRole("ADMIN", "USER")
		.antMatchers("/**").permitAll()
		.requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
		.and().csrf().disable();
	}
	
	
}
