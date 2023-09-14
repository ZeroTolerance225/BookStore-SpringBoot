package edu.psu.mobydickens.repository;

import com.google.common.collect.MoreCollectors;
import edu.psu.mobydickens.model.Book;
import edu.psu.mobydickens.model.DateTime;
import edu.psu.mobydickens.model.Genre;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private List<Book> bookList;

    public BookRepositoryImpl() {
        this.bookList = new ArrayList<>();
    }

    @Override
    public List<Book> getBooks() {
        return bookList;
    }

    @Override
    public void addBook(int bookID, String title, long ISBN, String author, int month, int day, int year,
                        Genre genre, double price) {
        DateTime datePublished = new DateTime(day, month, year);
        bookList.add(new Book(bookID, title, ISBN, author, datePublished, genre, price));
    }

    @Override // if the game object has the game id put in as a parameter then delete it
    public void deleteBook(int bookID) {
        bookList = bookList.stream().filter(g -> !Objects.equals(g.getBookID(), bookID)).collect(Collectors.toList());
    }

    @Override // returns a specific game with an id number that matches the one passed in
    public Book getBookById(int bookID) {
        return bookList.stream().filter(g -> Objects.equals(g.getBookID(), bookID))
                .collect(MoreCollectors.onlyElement());
    }
}

