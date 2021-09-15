package dev.patika.schoolmanagementsystem.core.utils;

import dev.patika.schoolmanagementsystem.core.constants.ResponseMessages;
import dev.patika.schoolmanagementsystem.core.results.DataResult;
import dev.patika.schoolmanagementsystem.core.results.Result;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Collections;

public class ResponseEntities {

    public static <T> ResponseEntity<DataResult<T>> okDataResult(T data) {
        return ResponseEntity.ok(DataResult.<T>builder().message(ResponseMessages.OK).payload(data).success(true).build());
    }

    public static <T> ResponseEntity<DataResult<T>> createdDataResult(T data, URI uri) {
        return ResponseEntity.created(uri).body(DataResult.<T>builder().message(ResponseMessages.CREATED).payload(data).success(true).build());
    }

    @SafeVarargs
    public static ResponseEntity<Result> notFoundResult(String name, Pair<String, Object>... parameters) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Result.fail(ResponseMessages.ERR_NOT_FOUND, ApiErrors.notFound(name, parameters)));
    }

    @SafeVarargs
    public static <T> ResponseEntity<DataResult<T>> notFoundDataResult(String name, Pair<String, Object>... parameters) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(DataResult.<T>builder().message(ResponseMessages.ERR_NOT_FOUND).errors(Collections.singletonList(ApiErrors.notFound(name, parameters))).build());
    }
}
