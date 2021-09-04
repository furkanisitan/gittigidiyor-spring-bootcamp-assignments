package dev.patika.schoolmanagementsystem.core.results.abstracts;

/**
 * See the package below to see the classes that implement this abstract class.
 *
 * @see dev.patika.schoolmanagementsystem.core.results.concretes
 */
public abstract class Result {

    private boolean success;
    private String message;

    //region constructors
    protected Result(boolean success) {
        this.success = success;
    }

    protected Result(boolean success, String message) {
        this(success);
        this.message = message;
    }
    //endregion

    //region getters & setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    //endregion
}
