package tr.gov.gomodor.rehbermaven.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tr.gov.gomodor.rehbermaven.entity.Menu;

@Stateless
public class MenuFacade extends AbstractFacade<Menu> {
    @PersistenceContext(unitName = "rehbermavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }
    
    public List<Menu> siraliMenuGetir(){
        
        return em.createNamedQuery("Menu.findAll").getResultList();
        
    }
    
}
