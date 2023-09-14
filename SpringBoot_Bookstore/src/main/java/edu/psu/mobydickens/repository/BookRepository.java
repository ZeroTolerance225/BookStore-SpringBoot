package edu.psu.mobydickens.repository;

import com.google.common.collect.MoreCollectors;
import edu.psu.mobydickens.model.Book;
import edu.psu.mobydickens.model.DateTime;
import edu.psu.mobydickens.model.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface BookRepository {
    List<Book> getBooks();

    void addBook(int bookID, String title, long ISBN, String author, int month, int day, int year,
                 Genre genre, double price);

    void deleteBook(int bookId);

    Book getBookById(int bookID);
}

