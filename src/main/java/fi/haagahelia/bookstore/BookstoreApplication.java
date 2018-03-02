package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository categoryRepository) {
		return (args) -> {
			
			Category crime = new Category("Crime");
			Category comedy = new Category("Comedy");
			Category drama = new Category("Drama");
			
			categoryRepository.save(crime);
			categoryRepository.save(comedy);
			categoryRepository.save(drama);
			
			Book b1 = new Book("The Witch", "Camilla Lackberg", 2017, "38949029-3", 35, crime);
			Book b2 = new Book("The Police", "Jo Nesbo", 2014, "3344909-6", 25, crime);
			Book b3 = new Book("Just One Look", "Harlan Coben", 2010, "9723897", 30, crime);
			
			repository.save(b1);
			repository.save(b2);
			repository.save(b3);
		};
	}
	
}
