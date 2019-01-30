package cz.cvut.fit.stovija5.tjv.sciarticles.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/articlesresources")
public class ArticlesEndpoint extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Stream.of(AuthorResource.class, 
                         ArticleResource.class, 
                         InstituteResource.class)
                .collect(Collectors.toSet());
    }
    
}
