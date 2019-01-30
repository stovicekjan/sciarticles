package cz.cvut.fit.stovija5.tjv.sciarticles.restclient;

import java.util.Arrays;
import java.util.Collection;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;



/**
 * Restful client. Enables CRUD operations over DTOs. To be used by Web UI.
 * Expected to be subclassed as singleton
 * @param <K>
 * @param <D> 
 */
public abstract class AbstractCRUDClient<K, D> {
    
    /**
     * Class of the entity (DTO) type.
     */
    private final Class<D> entityClass;

    /**
     * Class of the collection of entities (DTO) type.
     */
    private final Class<D[]> boxClass;
    
    public static final String ENDPOINT_URL = "http://localhost:8080/tvseries-backend/seriesresources";
    
    /**
     * JAX-RS client code.
     */
    private final Client client = ClientBuilder.newClient();

    /**
     * JAX-RS URL representation of on which requests may be invoked.
     */
    protected final WebTarget resourceTarget;

    protected AbstractCRUDClient(String resourceUri, Class<D> entityClass, Class<D[]> boxClass) {
        this.resourceTarget = client.target(ENDPOINT_URL + "/" + resourceUri);
        this.entityClass = entityClass;
        this.boxClass = boxClass;
    }
    
    /**
     * Store a representation of a new object. Invocation of POST HTTP operation
     * on /resource.
     *
     * @param e the object
     * @return entity value returned by the web service
     */
    public D createJson(D e) {
        return resourceTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.json(e), entityClass);
    }

    /**
     * Retrieve all objects of the entity type. Invocation of GET HTTP operation
     * on /resource
     *
     * @return a readonly collection containing the objects
     * @throws SeriesClientException if any error occurrs, e.g., service
     * unavailable
     */
    public Collection<D> retrieveAllJson() {
        try {
            return Arrays.asList(
                    resourceTarget
                            .request(MediaType.APPLICATION_JSON)
                            .get(boxClass)
            );
        } catch (WebApplicationException e) {
            throw new ArticlesClientException(e);
        }
    }
    
    /**
     * Store a new representation of an object (may be new or existing).
     * Invocation of PUT HTTP operation on /resource
     *
     * @param e the object to store
     */
    public void updateOrCreateJson(D e) {
        resourceTarget.request().put(Entity.json(e));
    }

    /**
     * Remove a representation of the object selected by its primary key value.
     * Invocation of DELETE HTTP operation on /resource/id
     *
     * @param id the primary key value
     */
    public void deleteById(K id) {
        resourceTarget.path("{i}").resolveTemplate("i", id).request().delete();
    }
}
