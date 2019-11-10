package uni.fmi.st.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.st.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(final String email, final String password);
}
