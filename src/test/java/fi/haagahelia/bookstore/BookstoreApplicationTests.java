package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.web.BookController;
import fi.haagahelia.bookstore.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookstoreApplicationTests {

	@Autowired
	private BookController controller;
	
	@Autowired
	private UserController userController;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
		assertThat(userController).isNotNull();
	}
	

}
