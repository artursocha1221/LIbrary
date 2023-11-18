package com.example.Library.dtos;

import com.example.Library.entities.Book;

public class BookDto {
    private String isbn;
    private String title;
    private String firstName, lastName;
    private Long numberOfPages;

    public BookDto(Book b) {
        this.isbn = b.getIsbn();
        this.title = b.getTitle();
        this.firstName = b.getFirstName();
        this.lastName = b.getLastName();
        this.numberOfPages = b.getNumberOfPages();
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
}
