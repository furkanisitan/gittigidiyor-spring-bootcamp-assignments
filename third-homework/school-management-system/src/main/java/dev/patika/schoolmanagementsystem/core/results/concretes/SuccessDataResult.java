package dev.patika.schoolmanagementsystem.core.results.concretes;

import dev.patika.schoolmanagementsystem.core.results.abstracts.DataResult;

public class SuccessDataResult<T> extends DataResult<T> {

    public SuccessDataResult(T data) {
        super(true, data);
    }

    public SuccessDataResult(T data, String message) {
        super(true, data, message);
    }
}
