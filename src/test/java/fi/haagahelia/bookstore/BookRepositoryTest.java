package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repository;
	
	@Test
	public void createNewBook() {
		Book book = new Book("Tartuffe", "Moliere", 1661, "2928379-3", 15, new Category("Comedy"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		Book book = repository.findByTitle("The Witch");
		repository.delete(book);
		List<Book> books = (List<Book>) repository.findAll();
		assertThat(books).hasSize(2);
		assertThat(books.get(0).getTitle()).isNotEqualTo("The Witch");
	}
	
	@Test
	public void findByTitle() {
		Book book = repository.findByTitle("The Witch");
		assertThat(book.getAuthor()).isEqualTo("Camilla Lackberg");
	}

}
