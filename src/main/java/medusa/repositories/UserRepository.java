package medusa.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import medusa.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	public User findByUsername(String username);
	public User findByEmail(String email);
	public Long countByEmail(String email);
	public Long countByUsername(String username);
	public List<User> findByRoleAndActive(String role, String active);
}
