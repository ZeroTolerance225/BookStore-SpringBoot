package edu.psu.mobydickens.controller;


import edu.psu.mobydickens.model.Book;
import edu.psu.mobydickens.model.Genre;
import edu.psu.mobydickens.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;


@Controller
public class SiteController {
    // make a list of books tto display as stock

    private final BookService bookService;

    public SiteController(BookService bookService) {
        this.bookService = bookService;
    }

    // Admin
    @GetMapping(value = "/admin/books/view")
    public String deleteBook(Model model) { // @RequestParam works too
        List<Book> bookList = bookService.getBooks();
        model.addAttribute("bookList", bookList);
        return "admin_books_view";
    }

    @GetMapping(value = "/admin/books/delete/{bookID}")
    public String deleteBook(Model model, @PathVariable int bookID) { // @RequestParam works too

        //  delete the game
        bookService.deleteBook(bookID);

        //  reload data
        List<Book> bookList = bookService.getBooks();
        model.addAttribute("bookList", bookList);
        return "admin_books_view";
    }

    @GetMapping(value = "/admin/books/add")
    public String addBookPage(Model model){

    return "admin_books_add";
    }



    @PostMapping(value = "/admin/books/add-submit")
    public String addBookSubmit(Model model, @RequestParam int bookID, @RequestParam String title, @RequestParam long ISBN,
                                 @RequestParam String author, @RequestParam int month, @RequestParam int day,
                                 @RequestParam int year, @RequestParam Genre genre, double price) {

        // Takes in the new data and then checks if there are any issues with what was submitted

        //  validate
        String errors = bookService.validateFormSubmit(title, ISBN, author, month, day, year, genre, price);
        if (errors != null) {
            //
            //  bad case!

            badCase(model, bookID, title, ISBN, author, month, day, year, genre, price, errors);
        } else {

            //
            //  good case!
            model.addAttribute("success", "Changes were saved");
            bookService.addBook(bookID, title, ISBN, author, month, day, year, genre, price);
        }
//
        //  reload the games
        List<Book> bookList = bookService.getBooks();
        model.addAttribute("bookList", bookList);


//        return "home";
        return "redirect:/admin/books/view";  // *****Redirects you back to a certain page
    }

    private void badCase(Model model, @RequestParam int bookID, @RequestParam String title, @RequestParam long ISBN, @RequestParam String author, @RequestParam int month, @RequestParam int day, @RequestParam int year, @RequestParam Genre genre, double price, String errors) {
        model.addAttribute("error", errors);
        model.addAttribute("bookID", bookID);
        model.addAttribute("title", title);
        model.addAttribute("ISBN", ISBN);
        model.addAttribute("author", author);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("year", year);
        model.addAttribute("genre", genre);
        model.addAttribute("price", price);
    }

    @GetMapping(value = "/admin/books/edit/{bookID}")
    public String editBookPage(Model model, @PathVariable int bookID) {
        //
        //  get game details
        Book book = bookService.getBookById(bookID);

        //
        //  reload data
        // Gets the games data and then sends it to the edit.html page for the user to view
        model.addAttribute("title", book.getTitle());
        model.addAttribute("ISBN", book.getISBN());
        model.addAttribute("author", book.getAuthor());
        model.addAttribute("datePublished", book.getDatePublished());
        model.addAttribute("genre", book.getGenre());
        model.addAttribute("price", book.getPrice());

        return "admin_books_edit";
    }



    @PostMapping(value = "/admin/books/edit")
    public String editBookSubmit(Model model, @RequestParam int bookID, @RequestParam String title, @RequestParam long ISBN,
                           @RequestParam String author, @RequestParam int month, @RequestParam int day,
                           @RequestParam int year, @RequestParam Genre genre, double price) {

        // Takes in the new data and then checks if there are any issues with what was submitted

        //  validate
        String errors = bookService.validateFormSubmit(title, ISBN, author, month, day, year, genre, price);
        if (errors != null) {
            //
            //  bad case!
            // adds them back to the edit page and tells the user to redo their submission

            badCase(model, bookID, title, ISBN, author, month, day, year, genre, price, errors);
        } else {


            //  good case!
            model.addAttribute("success", "Changes were saved");
            bookService.editBook(bookID, title, ISBN, author, month, day, year, genre, price);
        }
//
        //  reload the games
        List<Book> bookList = bookService.getBooks();
        model.addAttribute("bookList", bookList);


//        return "home";
        return "redirect:/admin/books/view";  // *****Redirects you back to a certain page
    }


    @GetMapping("/stock")
    public String stock(Model model) {
        // add in the existing books from the repository that you hardcoded in
        List<Book> bookList = bookService.getBooks();
        model.addAttribute("bookList", bookList); // sends in a list of games to be the parameters for the HTML page
        return "stock";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam String search) {

        // put all the games in a list, filters the list, and then sends the list back to the view
        List<Book> bookList = bookService.getBooks();

        // Holds the results from each of the searches and eliminates the duplicates
        Set<Book> filteredSet = new HashSet<>();

        //  filter out those that don't match
        filteredSet.addAll(bookList.stream() // open stream
                .filter(g -> g.getTitle().toLowerCase().contains(search.toLowerCase(Locale.ROOT))) // filter for title
                .toList()); //return to list

        //  filter out those that don't match
        filteredSet.addAll(bookList.stream() // open stream
                .filter(g -> g.getAuthor().toLowerCase().contains(search.toLowerCase(Locale.ROOT))) // filter for title
                .toList()); //return to list

        model.addAttribute("bookList", filteredSet.stream().toList());
        return "stock";
    }


    @GetMapping("/about")
    public String aboutPage(Model model) {
        return "about";
    }



    @GetMapping("/contact")
    public String contactPage(Model model) {
        return "contact";
    }
}
