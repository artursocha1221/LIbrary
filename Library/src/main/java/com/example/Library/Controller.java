package com.example.Library;

import com.example.Library.dtos.BookDto;
import com.example.Library.dtos.LendBookDto;
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


    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<Integer> addBook(@RequestBody Book book) {
        try {
            service.addBook(book);
        } catch (MultipleEntitiesException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return BAD_REQUEST;
        }
        return CREATED;
    }

    @RequestMapping(value = "/reader", method = RequestMethod.POST)
    public ResponseEntity<Integer> addReader(@RequestBody Reader reader) {
        try {
            service.addReader(reader);
        } catch (MultipleEntitiesException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return BAD_REQUEST;
        }
        return CREATED;
    }

    @RequestMapping(value = "/book", method = RequestMethod.PATCH)
    public ResponseEntity<Integer> changeStateOfBook(@RequestBody LendBookDto lendBookDto) {
        try {
            service.changeStateOfBook(lendBookDto.getIsbn(), lendBookDto.getPesel(), lendBookDto.getLend());
        } catch (NoEntityException | LentBookException e) {
            System.out.println(e.getMessage());
            return BAD_REQUEST;
        }
        return OK;
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity<BookDto> getBook(@RequestParam String isbn) {
        try {
            return new ResponseEntity<BookDto>(service.getBook(isbn), HttpStatus.OK);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            Book empty = new Book(null, null, null, null, 0L, 0L);
            return new ResponseEntity<BookDto>(new BookDto(empty), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/reader", method = RequestMethod.GET)
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
