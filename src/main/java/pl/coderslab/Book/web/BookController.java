package pl.coderslab.Book.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.Book.domain.Book;
import pl.coderslab.Book.domain.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
	
	private BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("")
	public List<Book> showBooks() {
		return bookService.getBooks();
	}
	
	@PostMapping("")
	public void addNewBook (@RequestBody Book book){    //HttpMessageConverter > konwersja danych HTTP na obiekt Javy
	bookService.addBook(book);
	}
	
	@GetMapping("/{id}")
	public Book findBook(@PathVariable long id) {
		return bookService.findBook(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Missing product with id: " + id));
	}
	
	@PutMapping("")
	public void editBook(@RequestBody Book book) {
		boolean ifUpdated = bookService.editBook(book);
		if (!ifUpdated) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id: " + book.getId() + " not updated");
		}
	}
	
	@DeleteMapping("{id}")
	public void removeBook(@PathVariable long id) {
		boolean ifDeleted = bookService.deleteBook(id);
		if (!ifDeleted){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Missing product with id: " + id);
		}
	}
	
	
	
	
//	@RequestMapping("/helloBook")
//	public Book helloBook() {
//		return new Book(1L, "9788324631766", "Thinking in Java",
//				"Bruce Eckel", "Helion", "programming");
//
//
//	}
}