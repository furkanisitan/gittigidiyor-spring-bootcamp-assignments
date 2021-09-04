package dev.patika.hw02.dataaccess.abstracts;

import java.util.List;

/**
 * @param <T>  the type of the entity to handle
 * @param <ID> the type of the entity's identifier
 */
interface Repository<T, ID> {

    /**
     * Returns all instances of the type.
     *
     * @return all entities.
     */
    List<T> findAll();

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    T findById(ID id);

    /**
     * Returns a reference to the entity with the given identifier.
     *
     * @param id must not be {@literal null}.
     * @return a reference to the entity with the given identifier.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    T getById(ID id);

    /**
     * Saves a given entity.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity; will never be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
     */
    <S extends T> S save(S entity);

    /**
     * Updates a given entity.
     *
     * @param entity must not be {@literal null}.
     * @param <S>    the updated entity; will never be {@literal null}.
     * @return the updated entity; will never be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
     */
    <S extends T> S update(S entity);

    /**
     * Deletes a given entity.
     *
     * @param entity must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
     */
    void delete(T entity);

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}.
     */
    void deleteById(ID id);
}
