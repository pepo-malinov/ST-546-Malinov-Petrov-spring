package uni.fmi.st.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@PostMapping("/removeMyPost")
	public ResponseEntity<String> removePost
						(@RequestParam(name = "id") int id,
										HttpSession session){
		final User user = (User) session.getAttribute("currentUser");
		if (null == user) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("");
		}
		List<Post> posts = user.getPosts();
		Post postForRemove = posts.stream()
							.filter(post->id == post.getId())
							.findFirst().orElse(null);
		if(null != postForRemove) {
			posts.remove(postForRemove);
			//session.setAttribute("currentUser", userRepo.save(user));
			postRepo.delete(postForRemove);
		}
		return ResponseEntity.ok().body("Post with id: "+ id + " is removed");
	}
	
	@GetMapping("/getPosts")
	public ResponseEntity<List<Post>> getAllPosts(HttpSession session) {
		final List<Post> posts = new ArrayList<>();
		final User user = (User) session.getAttribute("currentUser");
		if (null == user) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(posts);
		} else {
			posts.addAll(postRepo.findByPublicPost(user));
		}
		return ResponseEntity.ok(posts);
	}

	@PostMapping("/createPost")
	public ResponseEntity<Post> createPost(@RequestParam(name = "comment") String comment,
			@RequestParam(name = "place") String place, @RequestParam(name = "temp") float temp, HttpSession session) {

		final User user = (User) session.getAttribute("currentUser");
		if (null == user) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		final Post post = new Post(comment, place, temp);
		post.setOwner(user);
		user.addPost(post);
		session.setAttribute("currentUser", userRepo.save(user));

		return ResponseEntity.ok(post);
	}

}
