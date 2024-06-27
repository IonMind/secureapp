package com.defender.secureapp.ExceptionHandler;

import java.util.NoSuchElementException;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NoValuePresentException extends NoSuchElementException {
    private String Message;
}

