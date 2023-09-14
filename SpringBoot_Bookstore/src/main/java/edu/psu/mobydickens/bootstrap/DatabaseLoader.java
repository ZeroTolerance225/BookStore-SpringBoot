package edu.psu.mobydickens.bootstrap;

import edu.psu.mobydickens.model.Book;
import edu.psu.mobydickens.model.Genre;
import edu.psu.mobydickens.repository.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final BookRepository bookRepository;

    public DatabaseLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("It works");

        //
        //  populate the games
        bookRepository.addBook(0,"Hunger Games", 978043, "Suzanne Collins", 7, 3, 2008, Genre.fiction, 14.99);
        bookRepository.addBook(1,"Maze Runner", 573443, "James Dashner", 12, 10, 2009, Genre.fiction, 17.99);
        bookRepository.addBook(2,"The Lightning Thief", 293043, "Rick Riordan", 1, 7, 2005, Genre.fiction, 19.99);
        bookRepository.addBook(3,"A Brave New World", 3928323, "Aldous Huxley", 5, 3, 1932, Genre.scifi, 23.99);
        bookRepository.addBook(4,"1984", 7392983, "George Orwell", 6, 12, 1949, Genre.scifi, 33.99);
        bookRepository.addBook(5,"Scorch Trials", 931808, "James Dashner", 1, 7, 2012, Genre.adventure, 19.99);
        bookRepository.addBook(6,"The Great Gatsby", 284724, "F Scott Fitzgerald", 5, 29, 1925, Genre.tragedy, 37.99);
        bookRepository.addBook(7,"Catch 22", 2384723, "Joseph Heller", 10, 10, 1961, Genre.fiction, 17.99);
        bookRepository.addBook(8,"Ulysses", 3424324, "James Joyce", 2, 2, 1922, Genre.adventure, 19.99);
    }
}

