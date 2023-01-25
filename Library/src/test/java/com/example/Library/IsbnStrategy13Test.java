package com.example.Library;

import com.example.Library.validators.isbnValidator.IsbnStrategy13;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IsbnStrategy13Test {
    private IsbnStrategy13 example;

    @BeforeEach
    void createExample() {
        example = new IsbnStrategy13();
    }

    @Test
    void shouldReturnTrue() {
        assertTrue(example.checkIsbn("978-3-16-148410-0"));
    }

    @Test
    void shouldReturnTrue2() {
        assertTrue(example.checkIsbn("978-83-7181-510-2"));
    }

    @Test
    void shouldReturnFalse() {
        assertFalse(example.checkIsbn("978-83-7181-510-1"));
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
                () -> example.checkIsbn("978-83-7181-510-22"));
        assertEquals(exception.getMessage(), "Wrong length of isbn");
    }

    @Test
    void shouldThrowIllegalArgumentException2() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> example.checkIsbn("978-83-781-510-2"));
        assertEquals(exception.getMessage(), "Wrong length of isbn");
    }
}
