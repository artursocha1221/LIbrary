package com.example.Library.dtos;

public class LendBookDto {
    private String isbn;
    private String pesel;
    private Boolean lend;

    public LendBookDto(String isbn, String pesel, Boolean lend) {
        this.isbn = isbn;
        this.pesel = pesel;
        this.lend = lend;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getPesel() {
        return pesel;
    }

    public Boolean getLend() {
        return lend;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setLend(Boolean lend) {
        this.lend = lend;
    }
}
