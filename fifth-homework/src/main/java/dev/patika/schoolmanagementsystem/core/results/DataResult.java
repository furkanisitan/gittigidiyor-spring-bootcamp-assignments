package dev.patika.schoolmanagementsystem.core.results;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DataResult<T> extends Result {

    private final T payload;

    @Builder
    public DataResult(boolean success, String message, List<String> errors, T payload) {
        super(success, message, errors);
        this.payload = payload;
    }
}
