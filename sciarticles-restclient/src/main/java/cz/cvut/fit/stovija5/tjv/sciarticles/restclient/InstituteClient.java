package cz.cvut.fit.stovija5.tjv.sciarticles.restclient;

import cz.cvut.fit.stovija5.tjv.sciarticles.service.AuthorDTO;
import cz.cvut.fit.stovija5.tjv.sciarticles.service.InstituteDTO;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class InstituteClient extends AbstractCRUDClient<Long, InstituteDTO> {
    
    private final static InstituteClient INSTANCE = new InstituteClient();
    
    private InstituteClient() {
        super("institutes", InstituteDTO.class, InstituteDTO[].class);
    }

    public static InstituteClient getInstance() {
        return INSTANCE;
    }

    /**
     * add an author to the institute. Invoke a POST HTTP operation
     * @param id key of the institute
     * @param author 
     */
    public void addAuthorJson(long id, AuthorDTO author) {
        if (resourceTarget
                .path("{id}")
                .resolveTemplate("id", id)
                .queryParam("remove", false)
                .request()
                .post(Entity.json(author))
                .getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
            throw new ArticlesClientException(null);
        }
    }
    
    public void removeAuthorJson(long id, AuthorDTO author) {
        if (resourceTarget
                .path("{id}")
                .resolveTemplate("id", id)
                .queryParam("remove", false)
                .request()
                .post(Entity.json(author))
                .getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
            throw new ArticlesClientException(null);
        }
    }
}
