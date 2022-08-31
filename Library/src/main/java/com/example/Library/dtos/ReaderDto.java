package com.example.Library.dtos;

import com.example.Library.entities.Reader;

import java.util.ArrayList;

public class ReaderDto {
    private String pesel;
    private String firstName, lastName;
    private ArrayList<String> booksIsbn;

    public ReaderDto(Reader reader, ArrayList<String> booksIsbn) {
        this.pesel = reader.getPesel();
        this.firstName = reader.getFirstName();
        this.lastName = reader.getLastName();
        this.booksIsbn = booksIsbn;
    }

    public String getPesel() {
        return pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<String> getBooksIsbn() {
        return booksIsbn;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBooksIsbn(ArrayList<String> booksIsbn) {
        this.booksIsbn = booksIsbn;
    }
}
