package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
	return (args) -> {
		Book b1 = new Book("Yksi lensi yli käenpesän", "Milos Forman");
		Book b2 = new Book("Alkemisti", "Paulo Coelho");
		Book b3 = new Book("Kauas pilvet karkaavat", "Kjell Westö");
	
	};
	}

}
