package com.example.Library.isbnValidator;

public class IsbnStrategy13 implements IsbnStrategy {
    @Override
    public boolean checkIsbn(String isbn) {
        int sum = 0, realIndex = -1;
        for (int i = 0; i < isbn.length() - 1; ++i) {
            char c = isbn.charAt(i);
            if (!((c >= '0' && c <= '9') || c == '-'))
                return false;
            if (c != '-') {
                ++realIndex;
                sum += ((realIndex & 1) == 0) ? c - '0' : 3 * (c - '0');
            }
        }
        char shouldBe = (sum % 10 == 0) ? '0' : (char) (10 - (sum % 10) + '0');
        return isbn.charAt(isbn.length() - 1) == shouldBe;
    }
}
