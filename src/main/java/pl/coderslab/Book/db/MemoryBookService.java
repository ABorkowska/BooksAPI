package pl.coderslab.Book.db;

import org.springframework.stereotype.Component;
import pl.coderslab.Book.domain.Book;
import pl.coderslab.Book.domain.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MemoryBookService implements BookService {
	
	private static Long nextId = 4L;
	
	private List<Book> books = new ArrayList<>();
	
	public MemoryBookService() {
		books.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
		books.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
				"programming"));
		books.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
				"programming"));
	}
	
	public List<Book> getBooks() {
		return books;
	}
	
	@Override
	public void addBook(Book book) {
		book.setId(nextId);
		nextId++;
		books.add(book);
	}
	
	@Override
	public Optional<Book> findBook(long id) {
		return books.stream()
				.filter(b -> b.getId()==id)
				.findAny();
	}
	
	@Override
	public boolean editBook(Book book) {
		Optional<Book> bookToEdit = findBook(book.getId());
		if (bookToEdit.isPresent()) {
			int index = books.indexOf(bookToEdit.get());  //metoda Optional: get()
			books.set(index,book);
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteBook(long id) {
		Optional<Book> book = findBook(id);
		if (book.isPresent()){
			books.remove(book.get());
			return true;
		}
		return false;
	}
}

