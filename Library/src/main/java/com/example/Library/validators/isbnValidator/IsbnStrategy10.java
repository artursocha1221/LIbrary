package com.example.Library.validators.isbnValidator;

public class IsbnStrategy10 implements IsbnStrategy {
    @Override
    public boolean checkIsbn(String isbn) {
        if (isbn == null)
            throw new NullPointerException("Empty string");
        if (IsbnStrategy.countDigits(isbn) != 10)
            throw new IllegalArgumentException("Wrong length of isbn");
        int sum = 0, realIndex = 0;
        for (int i = 0; i < isbn.length() - 1; ++i) {
            char c = isbn.charAt(i);
            if (!((c >= '0' && c <= '9') || c == '-'))
                return false;
            if (c != '-') {
                ++realIndex;
                sum += realIndex * (c - '0');
            }
        }
        char shouldBe = (char) (sum % 11 + '0');
        return isbn.charAt(isbn.length() - 1) == shouldBe;
    }
}
