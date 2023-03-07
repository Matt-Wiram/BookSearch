package com.books.booksearch.bean;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String author;

    @Column
    private String title;

    @Column
    private String isbn;

    @OneToOne
    private User user;

    public Book(Long id, String author, String title, String isbn, User user) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.user = user;
    }

    public Book(String author, String title, String isbn, User user) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.user = user;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
