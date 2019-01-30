package cz.cvut.fit.stovija5.tjv.sciarticles.dao;

import cz.cvut.fit.stovija5.tjv.sciarticles.data.InstituteEntity;
import javax.ejb.Stateless;

@Stateless
public class InstituteDao extends Dao<Long, InstituteEntity>{

    public InstituteDao() {
        super(InstituteEntity.class);
    }

    @Override
    protected Long getEntityId(InstituteEntity e) {
        return e.getId();
    }
    
    
    
}
