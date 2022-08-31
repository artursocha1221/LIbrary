package com.example.Library.repos;

import com.example.Library.entities.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface BookRepo  extends CrudRepository<Book, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.whoHas = ?2 WHERE b.isbn = ?1")
    void updateWhoHas(String isbn, Long id);

    @Query("SELECT b FROM Book b WHERE b.isbn = ?1")
    ArrayList<Book> findByIsbn(String isbn);

    @Query("SELECT b.isbn FROM Book b WHERE b.whoHas = ?1")
    ArrayList<String> findByWhoHas(Long id);
}
