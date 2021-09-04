package dev.patika.hw02.core.results.helpers;

import dev.patika.hw02.core.results.abstracts.DataResult;
import dev.patika.hw02.core.results.concretes.ErrorDataResult;
import dev.patika.hw02.core.results.concretes.SuccessDataResult;

import java.util.Arrays;

public class DataResultHelper {

    public static <T> DataResult<T> ok(T data) {
        return new SuccessDataResult<>(data);
    }

    public static <T> DataResult<T> ok(T data, String message) {
        return new SuccessDataResult<>(data, message);
    }

    public static <T> DataResult<T> fail(String message) {
        return new ErrorDataResult<>(message);
    }

    public static <T> DataResult<T> fail(String message, String... errors) {
        return new ErrorDataResult<>(message, Arrays.asList(errors));
    }

}
