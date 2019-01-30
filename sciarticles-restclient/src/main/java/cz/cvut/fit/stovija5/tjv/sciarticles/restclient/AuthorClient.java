package cz.cvut.fit.stovija5.tjv.sciarticles.restclient;

import cz.cvut.fit.stovija5.tjv.sciarticles.service.ArticleDTO;
import cz.cvut.fit.stovija5.tjv.sciarticles.service.AuthorDTO;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class AuthorClient extends AbstractCRUDClient<Long, AuthorDTO> {
    
    private final static AuthorClient INSTANCE = new AuthorClient();
    
    private AuthorClient() {
        super("authors", AuthorDTO.class, AuthorDTO[].class);
    }

    public static AuthorClient getInstance() {
        return INSTANCE;
    }
    
    
    public void addArticleJson(long id, ArticleDTO article) {
        if (resourceTarget
                .path("{id}")
                .resolveTemplate("id", id)
                .queryParam("remove", false)
                .request()
                .post(Entity.json(article))
                .getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
            throw new ArticlesClientException(null);
        }
    }
    
    public void removeArticleJson(long id, ArticleDTO article) {
        if (resourceTarget
                .path("{id}")
                .resolveTemplate("id", id)
                .queryParam("remove", true)
                .request()
                .post(Entity.json(article))
                .getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
            throw new ArticlesClientException(null);
        }
    }
}
