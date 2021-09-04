package dev.patika.hw02.core.results.concretes;

import dev.patika.hw02.core.results.abstracts.DataResult;

public class SuccessDataResult<T> extends DataResult<T> {

    public SuccessDataResult(T data) {
        super(true, data);
    }

    public SuccessDataResult(T data, String message) {
        super(true, data, message);
    }
}
