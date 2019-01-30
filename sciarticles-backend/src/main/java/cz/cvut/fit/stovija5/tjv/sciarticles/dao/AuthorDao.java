package cz.cvut.fit.stovija5.tjv.sciarticles.dao;

import cz.cvut.fit.stovija5.tjv.sciarticles.data.ArticleEntity;
import cz.cvut.fit.stovija5.tjv.sciarticles.data.AuthorEntity;
import java.util.Objects;
import javax.ejb.Stateless;

@Stateless
public class AuthorDao extends Dao<Long, AuthorEntity>{

    public AuthorDao() {
        super(AuthorEntity.class);
    }

    @Override
    protected Long getEntityId(AuthorEntity e) {
        return e.getId();
    }
    
    public void addArticle(AuthorEntity author, ArticleEntity article) {
        Objects.requireNonNull(author);
        Objects.requireNonNull(article);
        
        author.getArticles().add(article);
        updateOrCreate(author);
    }
    
    public void addArticle(long authorId, ArticleEntity article) {
        Objects.requireNonNull(article);
        
        addArticle(retrieveById(authorId), article);
    }
    
    public void removeArticle(AuthorEntity author, ArticleEntity article) {
        Objects.requireNonNull(author);
        Objects.requireNonNull(article);
        
        author.getArticles().remove(article);
        updateOrCreate(author);
    }
    
    public void removeArticle(long authorId, ArticleEntity article) {
        Objects.requireNonNull(article);
        
        removeArticle(retrieveById(authorId), article);
        
    }
    
    
}
