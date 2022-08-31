package com.example.Library;

import com.example.Library.dtos.BookDto;
import com.example.Library.dtos.ReaderDto;
import com.example.Library.entities.Book;
import com.example.Library.entities.Reader;
import com.example.Library.exceptions.LentBookException;
import com.example.Library.exceptions.MultipleEntitiesException;
import com.example.Library.exceptions.NoEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class Controller {
    @Autowired
    Service service;

    private final ResponseEntity<Integer> BAD_REQUEST = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
    private final ResponseEntity<Integer> CREATED = new ResponseEntity<Integer>(HttpStatus.CREATED);
    private final ResponseEntity<Integer> OK = new ResponseEntity<Integer>(HttpStatus.OK);


    @PostMapping("/book")
    public ResponseEntity<Integer> addBook(@RequestParam String isbn,
                                           @RequestParam String title,
                                           @RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam Long numberOfPages) {
        try {
            service.addBook(new Book(isbn, title, firstName, lastName, numberOfPages, null));
        } catch (MultipleEntitiesException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return BAD_REQUEST;
        }
        return CREATED;
    }

    @PostMapping("/reader")
    public ResponseEntity<Integer> addReader(@RequestParam String pesel,
                                             @RequestParam String firstName,
                                             @RequestParam String lastName) {
        try {
            service.addReader(new Reader(pesel, firstName, lastName));
        } catch (MultipleEntitiesException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return BAD_REQUEST;
        }
        return CREATED;
    }

    @PutMapping("/book")
    public ResponseEntity<Integer> changeStateOfBook(@RequestParam String isbn,
                                                     @RequestParam String pesel,
                                                     @RequestParam Boolean lend) {
        try {
            service.changeStateOfBook(isbn, pesel, lend);
        } catch (NoEntityException | LentBookException e) {
            System.out.println(e.getMessage());
            return BAD_REQUEST;
        }
        return OK;
    }

    @GetMapping("/book")
    public ResponseEntity<BookDto> getBook(@RequestParam String isbn) {
        try {
            return new ResponseEntity<BookDto>(service.getBook(isbn), HttpStatus.OK);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            Book empty = new Book(null, null, null, null, 0L, 0L);
            return new ResponseEntity<BookDto>(new BookDto(empty), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/reader")
    public ResponseEntity<ReaderDto> getReader(@RequestParam String pesel) {
        try {
            return new ResponseEntity<ReaderDto>(service.getReader(pesel), HttpStatus.OK);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            Reader empty = new Reader(null, null, null);
            return new ResponseEntity<ReaderDto>(new ReaderDto(empty, new ArrayList<String>()), HttpStatus.BAD_REQUEST);
        }
    }
}
