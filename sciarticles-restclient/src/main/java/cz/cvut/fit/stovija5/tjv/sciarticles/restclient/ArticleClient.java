package cz.cvut.fit.stovija5.tjv.sciarticles.restclient;

import cz.cvut.fit.stovija5.tjv.sciarticles.service.ArticleDTO;

public class ArticleClient extends AbstractCRUDClient<Long, ArticleDTO>{
    
    private static final ArticleClient INSTANCE = new ArticleClient();
    
    private ArticleClient() {
        super("articles", ArticleDTO.class, ArticleDTO[].class);
    }

    public static ArticleClient getInstance() {
        return INSTANCE;
    }
}
