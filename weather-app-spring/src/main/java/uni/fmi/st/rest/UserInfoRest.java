/**
 * 
 */
package uni.fmi.st.rest;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uni.fmi.st.models.User;

/**
 * 
 * Provide base end points for user information
 * 
 * @author fmi
 *
 */
@RestController
public class UserInfoRest {

	@GetMapping("/getCurrentUser")
	public User getCurrentUser(final HttpSession session) {
		return (User) session.getAttribute("currentUser");
	}
}
