package tr.gov.gomodor.rehbermaven.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.eclipse.persistence.jaxb.TypeMappingInfo;

@ManagedBean
@SessionScoped
public class DilBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    
    public Locale getLocale(){
        
        return locale;
        
    }
    
    public void changeLanguage(String p_language){
        
        locale = new Locale(p_language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        
    }
    
}
