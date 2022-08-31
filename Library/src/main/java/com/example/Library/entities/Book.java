package com.example.Library.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private String title;
    private String firstName, lastName;
    private Long numberOfPages;
    private Long whoHas;

    public Book() {
    }

    public Book(String isbn, String title, String firstName, String lastName, Long numberOfPages, Long whoHas) {
        this.isbn = isbn;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfPages = numberOfPages;
        this.whoHas = whoHas;
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getNumberOfPages() {
        return numberOfPages;
    }

    public Long getWhoHas() {
        return whoHas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNumberOfPages(Long numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setWhoHas(Long whoHas) {
        this.whoHas = whoHas;
    }
}
