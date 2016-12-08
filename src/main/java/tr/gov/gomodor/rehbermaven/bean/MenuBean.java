package tr.gov.gomodor.rehbermaven.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import tr.gov.gomodor.rehbermaven.entity.Menu;
import tr.gov.gomodor.rehbermaven.service.MenuService;

@ManagedBean
@SessionScoped
public class MenuBean {
    
    @EJB
    private MenuService menuService;
    
    private DefaultMenuModel menuModel;

    public MenuBean() {
        
        menuModel = new DefaultMenuModel();
        
    }
    
    @PostConstruct
    public void menuDoldur(){
        
        DefaultSubMenu subMenu = new DefaultSubMenu("Menü");
        
        List<Menu> menuListe = menuService.siraliMenuGetir();
        
        for (Menu menu : menuListe) {
            
            DefaultMenuItem menuItem = new DefaultMenuItem(menu.getAd());
            menuItem.setCommand(menu.getLink());
            menuItem.setIcon(menu.getIcon());
            subMenu.addElement(menuItem);
            
        }
        
        menuModel.addElement(subMenu);
        
    }
    
    public MenuModel getModel(){
        
        return menuModel;
        
    }
    
}
