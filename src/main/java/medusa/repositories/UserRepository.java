package medusa.repositories;

import org.springframework.data.repository.CrudRepository;

import medusa.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	public User findByUsername(String username);
	 User findByEmail(String email);
	 Long countByEmail(String email);
	 Long countByUsername(String username);
}
