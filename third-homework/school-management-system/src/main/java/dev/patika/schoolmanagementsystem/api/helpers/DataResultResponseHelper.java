package dev.patika.schoolmanagementsystem.api.helpers;

import dev.patika.schoolmanagementsystem.core.constants.ResponseMessage;
import dev.patika.schoolmanagementsystem.core.helpers.ApiErrors;
import dev.patika.schoolmanagementsystem.core.results.abstracts.DataResult;
import dev.patika.schoolmanagementsystem.core.results.helpers.DataResultHelper;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DataResultResponseHelper {

    @SafeVarargs
    public static <T> ResponseEntity<DataResult<T>> notFound(String name, Pair<String, Object>... parameters) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(DataResultHelper.fail(ResponseMessage.NOT_FOUND, ApiErrors.entityNotFound(name, parameters)));
    }
}
