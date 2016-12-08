package tr.gov.gomodor.rehbermaven.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tr.gov.gomodor.rehbermaven.entity.Kisi;

@Stateless
public class KisiFacade extends AbstractFacade<Kisi> {
    @PersistenceContext(unitName = "rehbermavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KisiFacade() {
        super(Kisi.class);
    }
    
    public List<String> isimleriGetir(){
        
        return em.createQuery("select k.ad from Kisi k").getResultList();
        
    }
    
}
