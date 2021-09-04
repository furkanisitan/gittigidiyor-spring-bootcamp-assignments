package dev.patika.hw02.dataaccess.abstracts;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@org.springframework.stereotype.Repository
@Transactional(readOnly = true)
public abstract class BaseRepository<T, ID> implements Repository<T, ID> {

    private final Class<T> persistentClass;
    private final EntityManager entityManager;

    @SuppressWarnings("unchecked")
    protected BaseRepository(EntityManager entityManager) {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.entityManager = entityManager;
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery(String.format("from %s", persistentClass.getSimpleName()), persistentClass).getResultList();
    }

    @Override
    public T findById(ID id) {
        Assert.notNull(id, "id must not be null");
        return entityManager.find(persistentClass, id);
    }

    @Override
    public T getById(ID id) {
        Assert.notNull(id, "id must not be null");
        return entityManager.getReference(persistentClass, id);
    }

    @Transactional
    @Override
    public <S extends T> S save(S entity) {
        Assert.notNull(entity, "entity must not be null");
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public <S extends T> S update(S entity) {
        Assert.notNull(entity, "entity must not be null");
        return entityManager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "entity must not be null");
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public void deleteById(ID id) {
        Assert.notNull(id, "id must not be null");
        delete(getById(id));
    }
}
