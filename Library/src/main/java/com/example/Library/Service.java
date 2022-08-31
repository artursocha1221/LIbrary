package com.example.Library;

import com.example.Library.dtos.BookDto;
import com.example.Library.dtos.ReaderDto;
import com.example.Library.entities.Book;
import com.example.Library.entities.Reader;
import com.example.Library.exceptions.LentBookException;
import com.example.Library.exceptions.MultipleEntitiesException;
import com.example.Library.exceptions.NoEntityException;
import com.example.Library.isbnValidator.IsbnContext;
import com.example.Library.isbnValidator.IsbnStrategy10;
import com.example.Library.isbnValidator.IsbnStrategy13;
import com.example.Library.repos.BookRepo;
import com.example.Library.repos.ReaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Service {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private ReaderRepo readerRepo;

    private int countDigits(String text) {
        int cnt = 0;
        for (int i = 0; i < text.length(); ++i) {
            if (text.charAt(i) >= '0' && text.charAt(i) <= '9')
                ++cnt;
        }
        return cnt;
    }

    public void addBook(Book book)
            throws MultipleEntitiesException, IllegalArgumentException {
        if (bookRepo.findByIsbn(book.getIsbn()).size() != 0)
            throw new MultipleEntitiesException("Multiple books");
        if (countDigits(book.getIsbn()) == 13) {
            if (!(new IsbnContext(new IsbnStrategy13(), book.getIsbn()))
                    .isIsbnOk()) {
                throw new IllegalArgumentException("Illegal ISBN13");
            }
        }
        else {
            if (countDigits(book.getIsbn()) == 10) {
                if (!(new IsbnContext(new IsbnStrategy10(), book.getIsbn()))
                        .isIsbnOk())
                    throw new IllegalArgumentException("Illegal ISBN10");
            }
            else
                throw new IllegalArgumentException("It's neither ISBN13 nor ISBN10");
        }

        bookRepo.save(book);
    }

    private boolean isCorrectPESEL(String PESEL) {
        if (PESEL.length() != 11)
            return false;
        int[] costs = new int[] {1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};
        int sum = 0;
        for (int i = 0; i < PESEL.length(); ++i) {
            char c = PESEL.charAt(i);
            if (!(c >= '0' && c <= '9'))
                return false;
            sum += (c - '0') * costs[i];
        }
        return sum % 10 == 0;
    }

    public void addReader(Reader reader)
            throws MultipleEntitiesException, IllegalArgumentException {
        if (readerRepo.findByPesel(reader.getPesel()).size() != 0)
            throw new MultipleEntitiesException("Multiple readers");
        if (!isCorrectPESEL(reader.getPesel()))
            throw new IllegalArgumentException("Illegal PESEL");

        readerRepo.save(reader);
    }

    public void changeStateOfBook(String isbn, String pesel, Boolean lend)
            throws NoEntityException, LentBookException {
        ArrayList<Reader> readers = readerRepo.findByPesel(pesel);
        ArrayList<Book> books = bookRepo.findByIsbn(isbn);
        if (readers.size() * books.size() == 0)
            throw new NoEntityException("Required entity doesn't exist");
        if (lend && books.get(0).getWhoHas() != null)
            throw new LentBookException("Required book is lent");
        if (!lend && books.get(0).getWhoHas() == null)
            throw new LentBookException("Required book is in library");

        bookRepo.updateWhoHas(isbn, lend ? readers.get(0).getId() : null);
    }

    public BookDto getBook(String isbn)
            throws NoEntityException {
        ArrayList<Book> books = bookRepo.findByIsbn(isbn);
        if (books.size() == 0)
            throw new NoEntityException("Required entity doesn't exist");

        return new BookDto(books.get(0));
    }

    public ReaderDto getReader(String pesel)
            throws NoEntityException {
        ArrayList<Reader> readers = readerRepo.findByPesel(pesel);
        if (readers.size() == 0)
            throw new NoEntityException("Required entity doesn't exist");

        ArrayList<String> booksIsbn = bookRepo.findByWhoHas(readers.get(0).getId());
        return new ReaderDto(readers.get(0), booksIsbn);
    }
}
