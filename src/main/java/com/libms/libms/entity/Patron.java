package com.libms.libms.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patrons")
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patron_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phone;
    @Column(name = "email")
    private String email;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_patron",
            joinColumns = @JoinColumn(name = "patron_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> borrowedBooks = new HashSet<>();

    public Patron() {

    }
    public Patron(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Set<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
