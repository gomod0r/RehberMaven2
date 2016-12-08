package tr.gov.gomodor.rehbermaven.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import tr.gov.gomodor.rehbermaven.entity.Menu;
import tr.gov.gomodor.rehbermaven.facade.MenuFacade;

@Stateless
public class MenuService {
    
    @EJB
    private MenuFacade menuFacade;
    
    public List<Menu> siraliMenuGetir(){
        
        return menuFacade.siraliMenuGetir();
        
    }
    
}
