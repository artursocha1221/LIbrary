package com.example.Library;

import com.example.Library.validators.isbnValidator.IsbnStrategy10;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IsbnStrategy10Test {
    private IsbnStrategy10 example;

    @BeforeEach
    void createExample() {
        example = new IsbnStrategy10();
    }

    @Test
    void shouldReturnTrue() {
        assertTrue(example.checkIsbn("0-306-40615-2"));
    }

    @Test
    void shouldReturnFalse() {
        assertFalse(example.checkIsbn("0-306-40615-3"));
    }

    @Test
    void shouldThrowNullPointerException() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> example.checkIsbn(null));
        assertTrue(exception.getMessage().equals("Empty string"));
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> example.checkIsbn("0-306-40615-32"));
        assertEquals(exception.getMessage(), "Wrong length of isbn");
    }

    @Test
    void shouldThrowIllegalArgumentException2() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> example.checkIsbn("0-306-415-32"));
        assertEquals(exception.getMessage(), "Wrong length of isbn");
    }
}
