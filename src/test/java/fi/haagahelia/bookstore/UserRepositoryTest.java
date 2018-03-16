package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;
	
	@Test
	public void createNewUser() {
		User user = new User("testuser", "$2a$10$VRlfA/vqlj1XJPEFUNclAOn84wZNbuKJIY22IXlWZLHlW3w2O0I.2", "test@test.com", "USER");
		repository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteUser() {
		User user = repository.findByUsername("user");
		repository.delete(user);
		List<User> users = (List<User>) repository.findAll();
		assertThat(users).hasSize(1);
		assertThat(users.get(0).getUsername()).isEqualTo("admin");
	
	}
	
	@Test
	public void findByUsername() {
		User user = repository.findByUsername("user");
		assertThat(user.getRole()).isEqualTo("USER");
	}
}
