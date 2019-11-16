package uni.fmi.st.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uni.fmi.st.models.Post;
import uni.fmi.st.models.User;
import uni.fmi.st.repos.PostRepository;
import uni.fmi.st.repos.UserRepository;

@RestController
public class PostManagerRest {

	private PostRepository postRepo;
	private UserRepository userRepo;

	@Autowired
	public PostManagerRest(PostRepository postRepo, UserRepository userRepo) {
		this.postRepo = postRepo;
		this.userRepo = userRepo;
	}

	@PostMapping("/createPost")
	public Post createPost(@RequestParam(name = "comment") String comment, @RequestParam(name = "place") String place,
			@RequestParam(name = "temp") float temp, HttpSession session) {
		final User user = (User) session.getAttribute("currentUser");
		final Post post = new Post(comment, place, temp);
		post.setOwner(user);
		user.addPost(post);	
		userRepo.save(user);
		postRepo.save(post);
		
		return post;
	}

}
