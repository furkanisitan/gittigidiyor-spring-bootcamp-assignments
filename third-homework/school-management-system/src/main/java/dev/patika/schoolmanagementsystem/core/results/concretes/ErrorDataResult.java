package dev.patika.schoolmanagementsystem.core.results.concretes;

import dev.patika.schoolmanagementsystem.core.results.abstracts.DataResult;

import java.util.ArrayList;
import java.util.List;

public class ErrorDataResult<T> extends DataResult<T> {

    private List<String> errors = new ArrayList<>();

    //region constructors
    public ErrorDataResult() {
        super(false, null);
    }

    public ErrorDataResult(List<String> errors) {
        super(false, null);
        this.errors = errors;
    }

    public ErrorDataResult(String message) {
        super(false, null, message);
    }

    public ErrorDataResult(String message, List<String> errors) {
        super(false, null, message);
        this.errors = errors;
    }
    //endregion

    //region getters & setters
    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
    //endregion
}
