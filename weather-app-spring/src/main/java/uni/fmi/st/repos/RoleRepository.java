package uni.fmi.st.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.st.models.Role;

@Repository
public interface RoleRepository 
		extends JpaRepository<Role, Integer> {

}
