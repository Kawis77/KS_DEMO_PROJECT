package com.example.demo.validation;

import java.util.List;

public class ValidationError {
    private List<String> errors;


    public ValidationError(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

}
