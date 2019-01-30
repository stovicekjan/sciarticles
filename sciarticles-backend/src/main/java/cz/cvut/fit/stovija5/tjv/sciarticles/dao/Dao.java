package cz.cvut.fit.stovija5.tjv.sciarticles.dao;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * JPA controller. Works with the DB defined in the persistence unit.
 * @param <K> Primary key type
 * @param <E> Entity type
 */
public abstract class Dao<K, E> {
    
    @PersistenceContext(unitName = "cz.cvut.fit.stovija5.tjv.sciarticles_sciarticles-backend_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    private final Class<E> entityClass;
    
    /**
     * Initialize entity class object.
     * @param entityClass 
     * @throws NullPointerException if e is null
     */
    protected Dao(Class<E> entityClass) {
        Objects.requireNonNull(entityClass);
        this.entityClass = entityClass;
    }
    
    /**
     * Persist the entity to the DB.
     * @param e value of the entity
     * @return entity object
     * @throws NullPointerException is e is null
     */
    public E create(E e) {
        entityManager.persist(Objects.requireNonNull(e));
        return e;
    }
    
    public E retrieveById(K id) {
        Objects.requireNonNull(id);
        return entityManager.find(entityClass, id);
    }
    
    public Collection<E> retrieveAll() {
        return entityManager.createQuery(
                entityManager.getCriteriaBuilder().createQuery(entityClass)
        ).getResultList();
    }
    
    public E updateOrCreate(E e) {
        return entityManager.merge(Objects.requireNonNull(e));
    }
    
    public void deleteById(K id) {
        final E e = retrieveById(id);
        if (e != null)
            entityManager.remove(e);
    }
    
    protected abstract K getEntityId(E e);
    
    public boolean exists(E e) {
        Objects.requireNonNull(e);
        return retrieveById(getEntityId(e)) != null;
    }
    
    public boolean existsById(K id) {
        return retrieveById(id) != null;
    }
}
