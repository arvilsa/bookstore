package fi.haagahelia.bookstore.controller;

import java.util.List;
import java.util.Optional;

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
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
    @RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "booklist";
        //palauttaa booklist.html-view:n
    }
    
	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
        //palauttaa JSON listan
    }    

	// RESTful service to get books by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    } 
    
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());
        return "addbook";
    } 
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    } 
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:../booklist";
    } 
    
    @RequestMapping(value="/edit/{id}")
    public String editbook(@PathVariable("id") Long bookId, Model model) {	
    	model.addAttribute("categories", crepository.findAll());    	
        model.addAttribute("book", repository.findById(bookId));
        return "editbook";
    }
    

    
 
    

}
