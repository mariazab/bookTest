package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository repository;
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Fantasy");
		repository.save(category);
		assertThat(category.getId()).isNotNull();
	}
	
	@Test
	public void deleteCategory() {
		Category category = repository.findByName("Crime");
		repository.delete(category);
		List<Category> categories = (List<Category>) repository.findAll();
		assertThat(categories).hasSize(2);
		assertThat(categories.get(0)).isNotEqualTo("Crime");
	}
	
	@Test
	public void findByName() {
		Category category = repository.findByName("Comedy");
		assertThat(category.getId()).isEqualTo(2);
	}
}
