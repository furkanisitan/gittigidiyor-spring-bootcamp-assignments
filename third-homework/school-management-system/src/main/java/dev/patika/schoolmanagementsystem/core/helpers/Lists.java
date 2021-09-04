package dev.patika.schoolmanagementsystem.core.helpers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Lists {

    /**
     * Converts {@literal Iterable<T>} to {@literal List<T>}.
     *
     * @param elements an Iterable<T>.
     * @param <T>      the type of elements.
     * @return a List<T> containing the elements.
     */
    public static <T> List<T> from(Iterable<T> elements) {
        return StreamSupport.stream(elements.spliterator(), false).collect(Collectors.toList());
    }
}
