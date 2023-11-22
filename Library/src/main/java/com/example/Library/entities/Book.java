package com.example.Library.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
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
}