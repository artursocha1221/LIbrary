package com.example.Library.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LendBookDto {
    private String isbn;
    private String pesel;
    private Boolean lend;
}
