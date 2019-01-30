package cz.cvut.fit.stovija5.tjv.sciarticles.service;

import cz.cvut.fit.stovija5.tjv.sciarticles.dao.ArticleDao;
import cz.cvut.fit.stovija5.tjv.sciarticles.dao.Dao;
import cz.cvut.fit.stovija5.tjv.sciarticles.data.ArticleEntity;
import cz.cvut.fit.stovija5.tjv.sciarticles.service.ArticleDTO;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Path("/articles")
@Stateless
public class ArticleResource extends AbstractCRUDResource<Long, ArticleEntity, ArticleDTO> {
    protected static final Function<ArticleEntity, ArticleDTO> ENTITY_TO_DTO_CONVERTER
            = e -> e == null ? null : new ArticleDTO(
                                        e.getId(), 
                                        e.getTitle(), 
                                        e.getIssue(), 
                                        e.getPage(),
                                        e.getPublished() == null ? null : LocalDateTime.ofInstant(e.getPublished().toInstant(), ZoneId.systemDefault())
                                        );
    
    protected static final Function<ArticleDTO, ArticleEntity> DTO_TO_ENTITY_CONVERTER
            = d -> new ArticleEntity(
                    d.getId(),
                    d.getTitle(), 
                    d.getIssue(), 
                    d.getPage(), 
                    d.getPublished() == null ? null : Date.from(d.getPublished().atZone(ZoneId.systemDefault()).toInstant()), 
                    null);
    
    @EJB
    private ArticleDao controller;

    public ArticleResource() {
        super(ENTITY_TO_DTO_CONVERTER, DTO_TO_ENTITY_CONVERTER);
    }

    @Override
    protected Dao<Long, ArticleEntity> getController() {
        return controller;
    }
    
    
    
    
    
    
       
    
}
