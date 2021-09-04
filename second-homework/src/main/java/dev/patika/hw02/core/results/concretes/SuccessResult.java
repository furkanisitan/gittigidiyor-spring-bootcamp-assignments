package dev.patika.hw02.core.results.concretes;

import dev.patika.hw02.core.results.abstracts.Result;

public class SuccessResult extends Result {

    public SuccessResult() {
        super(true);
    }

    public SuccessResult(String message) {
        super(true, message);
    }
}
