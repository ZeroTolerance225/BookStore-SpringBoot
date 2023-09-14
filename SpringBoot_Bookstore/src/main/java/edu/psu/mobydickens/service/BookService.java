package edu.psu.mobydickens.service;

import edu.psu.mobydickens.model.Book;
import edu.psu.mobydickens.model.DateTime;
import edu.psu.mobydickens.model.Genre;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    String validateFormSubmit(String title, long ISBN, String author, int month,
                              int day, int year, Genre genre, double price);

    void addBook(int bookID, String title, long ISBN, String author, int month, int day, int year,
                 Genre genre, double price);

    void deleteBook(int bookID);

    Book getBookById(int bookID);

    void editBook(int bookID, String title, long ISBN, String author, int month, int day, int year,
                  Genre genre, double price);
}