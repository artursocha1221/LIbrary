package com.example.Library.repos;

import com.example.Library.entities.Reader;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ReaderRepo extends CrudRepository<Reader, Long> {
    @Query("SELECT r FROM Reader r WHERE r.pesel = ?1")
    ArrayList<Reader> findByPesel(String pesel);
}
