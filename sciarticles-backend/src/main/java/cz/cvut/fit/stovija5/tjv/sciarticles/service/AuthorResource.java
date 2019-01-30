package cz.cvut.fit.stovija5.tjv.sciarticles.service;

import cz.cvut.fit.stovija5.tjv.sciarticles.dao.AuthorDao;
import cz.cvut.fit.stovija5.tjv.sciarticles.dao.Dao;
import cz.cvut.fit.stovija5.tjv.sciarticles.data.AuthorEntity;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


//TODO nema byt stateless???
@Path("/authors")
public class AuthorResource extends AbstractCRUDResource<Long, AuthorEntity, AuthorDTO>{
    
    @EJB
    private AuthorDao controller;

    public AuthorResource() {
        super(
                e -> e == null ? null : new AuthorDTO(
                        e.getId(), 
                        e.getName(),
                        e.getSurname(),
                        e.getArticles().stream()
                                .map(ArticleResource.ENTITY_TO_DTO_CONVERTER)
                                .collect(Collectors.toSet())),
                d -> new AuthorEntity(
                        d.getId(), 
                        d.getName(), 
                        d.getSurname(),
                        null,
                        d.getArticles().stream()
                                .map(ArticleResource.DTO_TO_ENTITY_CONVERTER)
                                .collect(Collectors.toSet()))
        );
    }
    
    protected static final Function<AuthorEntity, AuthorDTO> ENTITY_TO_DTO_CONVERTER
            = e -> e == null ? null : new AuthorDTO(
                    e.getId(),
                    e.getName(),
                    e.getSurname(),
                    e.getArticles().stream().map(ArticleResource.ENTITY_TO_DTO_CONVERTER).collect(Collectors.toSet()));
    
    protected static final Function<AuthorDTO, AuthorEntity> DTO_TO_ENTITY_CONVERTER
            = d -> new AuthorEntity(
                    d.getId(), 
                    d.getName(), 
                    d.getSurname(), 
                    null, 
                    d.getArticles().stream().map(ArticleResource.DTO_TO_ENTITY_CONVERTER).collect(Collectors.toSet()));
    
    
    

    @Override
    protected Dao<Long, AuthorEntity> getController() {
        return controller;
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{primaryKey}")
    public Response addOrRemoveArticle(
            @PathParam("primaryKey") long authorId,
            ArticleDTO article,
            @QueryParam("remove") boolean remove) {
        
        try {
            if (remove) {
                controller.removeArticle(authorId, ArticleResource.DTO_TO_ENTITY_CONVERTER.apply(article));
            } else {
                controller.addArticle(authorId, ArticleResource.DTO_TO_ENTITY_CONVERTER.apply(article));
            }
            
            return Response.noContent().build();
        } catch (EJBException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
    
    
    
}
