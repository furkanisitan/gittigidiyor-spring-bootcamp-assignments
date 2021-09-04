package dev.patika.hw01.core.helpers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {

    private final String persistenceUnitName;
    private EntityManagerFactory entityManagerFactory;

    public EntityManagerHelper(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }

    public EntityManager getEntityManager() {

        if (entityManagerFactory == null || !entityManagerFactory.isOpen())
            this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        return entityManagerFactory.createEntityManager();
    }

    public void close(EntityManager entityManager) {
        entityManager.clear();
        entityManager.close();
        entityManagerFactory.close();
    }

}
