package uni.fmi.st.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.st.models.Post;
import uni.fmi.st.models.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	public List<Post> findByPublicPost(Boolean publicPost);
	public List<Post> findByPublicPostTrue();
	public List<Post> findByPublicPost(final User owner);
}
