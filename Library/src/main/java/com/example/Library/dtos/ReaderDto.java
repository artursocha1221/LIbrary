package com.example.Library.dtos;

import com.example.Library.entities.Reader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
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
}
