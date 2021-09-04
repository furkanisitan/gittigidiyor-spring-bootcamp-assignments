package dev.patika.schoolmanagementsystem.core.results.abstracts;

/**
 * See the package below to see the classes that implement this abstract class.
 *
 * @param <T> the type of the {@literal data}
 * @see dev.patika.schoolmanagementsystem.core.results.concretes
 */
public abstract class DataResult<T> extends Result {

    private T data;

    //region constructors
    public DataResult(boolean success, T data) {
        this(success, data, null);
    }

    public DataResult(boolean success, T data, String message) {
        super(success, message);
        this.data = data;
    }
    //endregion

    //region getters & setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    //endregion
}
