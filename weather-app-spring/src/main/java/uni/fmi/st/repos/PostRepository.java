package uni.fmi.st.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.st.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
