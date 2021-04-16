package pl.coderslab.Book.domain;

import java.util.List;
import java.util.Optional;

public interface BookService {
	List<Book> getBooks();
	void addBook (Book book);
	Optional <Book> findBook(long id);
	boolean editBook (Book book);
	boolean deleteBook (long id);
}
