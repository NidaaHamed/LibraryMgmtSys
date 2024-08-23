package com.libms.libms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "publication_year")
    private int publicationYear;
    @Column(name = "isbn")
    private String isbn;

    @JsonBackReference
    @ManyToMany(mappedBy = "borrowedBooks")
    @JsonIgnoreProperties("borrowedBooks")
    private Set<Patron> borrowedBy = new HashSet<>();

    // Constructors, getters, setters, and other methods

    public Book() {
        // Default constructor
    }

    public Book(String title, String author, int publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

    // Getters and setters for all fields

    // Title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Author
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Publication Year
    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    // ISBN
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    @JsonProperty("borrowedBy")
    public Set<Patron> getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(Set<Patron> borrowedBy) {
        this.borrowedBy = borrowedBy;
    }
}
