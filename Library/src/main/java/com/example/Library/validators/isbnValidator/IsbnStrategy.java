package com.example.Library.validators.isbnValidator;

public interface IsbnStrategy {
    boolean checkIsbn(String isbn);

    static int countDigits(String text) {
        int cnt = 0;
        for (int i = 0; i < text.length(); ++i) {
            if (text.charAt(i) >= '0' && text.charAt(i) <= '9')
                ++cnt;
        }
        return cnt;
    }
}
