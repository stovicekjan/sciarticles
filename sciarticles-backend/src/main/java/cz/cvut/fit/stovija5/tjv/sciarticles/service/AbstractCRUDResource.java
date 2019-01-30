package cz.cvut.fit.stovija5.tjv.sciarticles.service;

import cz.cvut.fit.stovija5.tjv.sciarticles.dao.Dao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Abstract RESTful resource for CRUD with any entity type. Works with a
 persistence layer of this application.
 * 
 * @param <K> Primary key type
 * @param <E> Entity type
 * @param <D> DTO type
 */
public abstract class AbstractCRUDResource<K, E, D> {
    
    protected final Function<E, D> entityToDtoConverter;
    protected final Function<D, E> dtoToEntityConverter;

    /** 
     * Initialize all Function fields. To be used in subclasses only.
     */
    protected AbstractCRUDResource(Function<E, D> entityToDtoConverter, 
                                Function<D, E> dtoToEntityConverter) {
        this.entityToDtoConverter = entityToDtoConverter;
        this.dtoToEntityConverter = dtoToEntityConverter;
    }
    
    /**
     * Get instance of JPA controller for particular entity type.
     * @return Instance of JPA controller for entity type E.
     */
    protected abstract Dao<K, E> getController();
    
    /**
     * Store an entity value. The value is a part of the POST message body.
     * @param dto The value of an entity
     * @return the entity object synchronized with the underlying store
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public D create(D dto) {
        return entityToDtoConverter.apply(getController().create(dtoToEntityConverter.apply(dto)));
    }
    
    /**
     * Retrieve an entity instance selected by its primary key value. The primary
     * key value is specified as the last part of the resource URL.
     * 
     * @param id Primary key value
     * @return An entity value corresponding to given primary key
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{primaryKey}")
    public D retrieveById(@PathParam("primaryKey") K id) {
        return entityToDtoConverter.apply(getController().retrieveById(id));
    }
    
    /**
     * Update or store an entity. The value is part of the PUT message body.
     * @param dto Entity value to update or store
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void updateOrCreate(D dto) {
        getController().updateOrCreate(dtoToEntityConverter.apply(dto));
    }
    
    /**
     * Delete a selected entity.
     * @param id Primary key of the selected entity
     */
    @DELETE
    @Path("/{primaryKey}")
    public void deleteById(@PathParam("primaryKey") K id) {
        getController().deleteById(id);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<D> retrieveAll() {
        Collection<E> allEntities = getController().retrieveAll();
        Collection<D> dtoCollection = new ArrayList<>();
        D dto;
        for (E entity : allEntities) {
            dto = entityToDtoConverter.apply(entity);
            dtoCollection.add(dto);
        }
        return dtoCollection;
    }
}
