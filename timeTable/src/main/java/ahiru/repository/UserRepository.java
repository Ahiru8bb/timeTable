package ahiru.repository;

import org.springframework.data.repository.CrudRepository;

import ahiru.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	public User findUserByUsername(String username);
}
