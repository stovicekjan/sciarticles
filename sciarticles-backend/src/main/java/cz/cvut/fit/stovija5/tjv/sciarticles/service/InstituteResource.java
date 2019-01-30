package cz.cvut.fit.stovija5.tjv.sciarticles.service;

import cz.cvut.fit.stovija5.tjv.sciarticles.dao.Dao;
import cz.cvut.fit.stovija5.tjv.sciarticles.dao.InstituteDao;
import cz.cvut.fit.stovija5.tjv.sciarticles.data.InstituteEntity;
import cz.cvut.fit.stovija5.tjv.sciarticles.service.InstituteDTO;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Path("/institutes")
@Stateless
public class InstituteResource extends AbstractCRUDResource<Long, InstituteEntity, InstituteDTO> {
   
    @EJB
    private InstituteDao controller;

    
    
    public InstituteResource() {
        super(
                e -> e == null ? null : new InstituteDTO(
                        e.getId(), 
                        e.getName(), 
                        e.getStreet(), 
                        e.getCity(), 
                        e.getCountry(), 
                        e.getAuthors().stream().map(AuthorResource.ENTITY_TO_DTO_CONVERTER).collect(Collectors.toSet())
                    ),
                d -> new InstituteEntity(
                        d.getId(), 
                        d.getName(), 
                        d.getStreet(), 
                        d.getCity(), 
                        d.getCountry(), 
                        d.getAuthors().stream().map(AuthorResource.DTO_TO_ENTITY_CONVERTER).collect(Collectors.toSet()))
        );
    }

    @Override
    protected Dao<Long, InstituteEntity> getController() {
        return controller;
    }
    
    
    
    
    
    
}
