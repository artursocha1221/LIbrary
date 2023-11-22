package com.example.Library.dtos;

import com.example.Library.entities.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
