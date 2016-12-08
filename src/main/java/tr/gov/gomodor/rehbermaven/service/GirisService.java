package tr.gov.gomodor.rehbermaven.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tr.gov.gomodor.rehbermaven.entity.Giris;
import tr.gov.gomodor.rehbermaven.facade.GirisFacade;

@Stateless
public class GirisService {
    
    @EJB
    private GirisFacade girisFacade;
    
    public boolean giriseYetkilimi(Giris p_giris){
        
        return girisFacade.girisKontrol(p_giris);
        
    }
    
}
