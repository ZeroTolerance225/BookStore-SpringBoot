package edu.psu.mobydickens.model;

public class Book {
    private int bookID;
    private String title;
    private long ISBN;
    private String author;
    private DateTime datePublished;
    private Genre genre;
    private double price;

    public Book(int bookID, String title, long ISBN, String author, DateTime datePublished, Genre genre, double price) {
        this.bookID = bookID;
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.datePublished = datePublished;
        this.genre = genre;
        this.price = price;
    }

    public Book(int bookID, String title, long ISBN, String author, int day, int month, int year, Genre genre, double price) {
        this.bookID = bookID;
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.datePublished = new DateTime(day, month, year);
        this.genre = genre;
        this.price = price;
    }
    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public DateTime getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(DateTime datePublished) {
        this.datePublished = datePublished;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
