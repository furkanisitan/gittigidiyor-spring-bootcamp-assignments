package dev.patika.schoolmanagementsystem.core.results.concretes;

import dev.patika.schoolmanagementsystem.core.results.abstracts.Result;

import java.util.ArrayList;
import java.util.List;

public class ErrorResult extends Result {

    private List<String> errors = new ArrayList<>();

    //region constructors
    public ErrorResult() {
        super(false);
    }

    public ErrorResult(List<String> errors) {
        super(false);
        this.errors = errors;
    }

    public ErrorResult(String message) {
        super(false, message);
    }

    public ErrorResult(String message, List<String> errors) {
        super(false, message);
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
