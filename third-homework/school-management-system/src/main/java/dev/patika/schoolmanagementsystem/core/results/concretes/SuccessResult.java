package dev.patika.schoolmanagementsystem.core.results.concretes;

import dev.patika.schoolmanagementsystem.core.results.abstracts.Result;

public class SuccessResult extends Result {

    public SuccessResult() {
        super(true);
    }

    public SuccessResult(String message) {
        super(true, message);
    }
}
