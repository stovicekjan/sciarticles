package cz.cvut.fit.stovija5.tjv.sciarticles.dao;

import cz.cvut.fit.stovija5.tjv.sciarticles.data.ArticleEntity;
import javax.ejb.Stateless;

@Stateless
public class ArticleDao extends Dao<Long, ArticleEntity>{

    public ArticleDao() {
        super(ArticleEntity.class);
    }

    @Override
    protected Long getEntityId(ArticleEntity e) {
        return e.getId();
    }
    
    
    
    
}
