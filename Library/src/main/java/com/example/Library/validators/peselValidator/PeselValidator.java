package com.example.Library.validators.peselValidator;

public class PeselValidator {
    private String pesel;

    public PeselValidator(String pesel) {
        this.pesel = pesel;
    }

    public boolean isPeselOk() {
        if (pesel.length() != 11)
            return false;
        int[] costs = new int[] {1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};
        int sum = 0;
        for (int i = 0; i < pesel.length(); ++i) {
            char c = pesel.charAt(i);
            if (!(c >= '0' && c <= '9'))
                return false;
            sum += (c - '0') * costs[i];
        }
        return sum % 10 == 0;
    }
}
