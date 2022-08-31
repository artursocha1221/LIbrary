package com.example.Library.isbnValidator;

public class IsbnContext {
    private IsbnStrategy isbnStrategy;
    private String isbn;

    public IsbnContext(IsbnStrategy isbnStrategy, String isbn) {
        this.isbnStrategy = isbnStrategy;
        this.isbn = isbn;
    }

    public boolean isIsbnOk() {
        return isbnStrategy.checkIsbn(isbn);
    }

}
