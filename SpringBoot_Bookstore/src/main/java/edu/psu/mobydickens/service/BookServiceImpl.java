package edu.psu.mobydickens.service;


import edu.psu.mobydickens.model.Book;
import edu.psu.mobydickens.model.DateTime;
import edu.psu.mobydickens.model.Genre;
import edu.psu.mobydickens.repository.BookRepository;
import edu.psu.mobydickens.repository.BookRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository gameRepository) {
        this.bookRepository = gameRepository;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }

    @Override
    public String validateFormSubmit(String title, long ISBN, String author, int month,
                                     int day, int year, Genre genre, double price) {
        if (!StringUtils.hasText(title)) {
            return "title is required";
        }
        if (!StringUtils.hasText(String.valueOf(ISBN))) {
            return "ISBN is required";
        }
        if (!StringUtils.hasText(author)) {
            return "author is required";
        }
        if (!StringUtils.hasText(String.valueOf(month))) {
            return "month is required";
        }
        if (!StringUtils.hasText(String.valueOf(day))) {
            return "day is required";
        }
        if (!StringUtils.hasText(String.valueOf(year))) {
            return "year is required";
        }
        if (!StringUtils.hasText(String.valueOf(genre))) {
            return "genre is required";
        }
        if (!StringUtils.hasText(String.valueOf(price))) {
            return "price is required";
        }

        return null;
    }

    @Override
    public void addBook(int bookID, String title, long ISBN, String author, int month, int day, int year,
                        Genre genre, double price) {

        bookRepository.addBook(bookID, title, ISBN, author, month, day, year, genre, price);
    }

    @Override
    public void deleteBook(int bookID) {
        bookRepository.deleteBook(bookID);
    }

    @Override
    public Book getBookById(int bookID) {
        return bookRepository.getBookById(bookID);
    }

    @Override
    public void editBook(int bookID, String title, long ISBN, String author, int month, int day, int year,
                         Genre genre, double price) {
        DateTime datePublished = new DateTime(day, month, year);
        var book = bookRepository.getBookById(bookID);
        if (book != null) {
            book.setBookID(bookID);
            book.setTitle(title);
            book.setISBN(ISBN);
            book.setAuthor(author);
            book.setDatePublished(datePublished);
            book.setGenre(genre);
            book.setPrice(price);
        }
    }
}
