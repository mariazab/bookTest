package fi.haagahelia.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.CategoryRepository;




@Controller
public class BookController {

	@Autowired
	BookRepository repository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String bookstore(Model model) {
          return "index";
    }
	
	// Show all books
	@RequestMapping("/booklist")
	public String booklist(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	// RESTful service to get all books
	@RequestMapping (value="/books", method=RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}
	
	//RESTful service to get book by id
	@RequestMapping(value="/book/{id}", method=RequestMethod.GET)
	public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {
		return repository.findOne(bookId);
	}
	
	
	
	// Delete book
	@RequestMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.delete(bookId);
		return "redirect:../booklist";
	}
	
	// Add new book
	@RequestMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());	
		model.addAttribute("categories", categoryRepository.findAll());
		return "addBook";
	}
	
	// Save new book
	@RequestMapping("/save") 
	public String saveBook(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	// Edit book
	@RequestMapping("/edit/{id}") 
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findOne(bookId));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editBook";
	}
	
	// Login 
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
}
