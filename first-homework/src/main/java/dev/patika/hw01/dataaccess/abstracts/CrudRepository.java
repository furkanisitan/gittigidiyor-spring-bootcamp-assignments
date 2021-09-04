package dev.patika.hw01.dataaccess.abstracts;

import java.util.List;

/**
 * @param <T>  the domain type the repository manages
 * @param <ID> the type of the id of the entity the repository manages
 */
public interface CrudRepository<T, ID> {

    List<T> findAll();

    T findById(ID id);

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    void deleteById(ID id);
}
