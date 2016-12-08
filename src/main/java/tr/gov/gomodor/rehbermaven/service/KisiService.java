package tr.gov.gomodor.rehbermaven.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import tr.gov.gomodor.rehbermaven.entity.Kisi;
import tr.gov.gomodor.rehbermaven.entity.Log;
import tr.gov.gomodor.rehbermaven.facade.KisiFacade;
import tr.gov.gomodor.rehbermaven.facade.LogFacade;
import tr.gov.gomodor.rehbermaven.util.JSFUtil;

@Stateless
public class KisiService {
    
    @EJB
    private KisiFacade kisiFacade;
    
    @EJB
    private LogFacade logFacade;
    
    public void guncelle(Kisi p_kisi){
        
        kisiFacade.edit(p_kisi);
        
        Log log = new Log();
        log.setKullanici(JSFUtil.getKullanici());
        log.setTarihsaat(new java.util.Date());
        log.setIslem("Güncelleme");
        log.setKisino(p_kisi.getNo());
        
        logFacade.create(log);
        
    }
    
    public void ekle(Kisi p_kisi){
        
        kisiFacade.create(p_kisi);
        
        Log log = new Log();
        log.setKullanici(JSFUtil.getKullanici());
        log.setTarihsaat(new java.util.Date());
        log.setIslem("Ekle");
        log.setKisino(p_kisi.getNo());
        
        logFacade.create(log);
        
    }
    
    public List<Kisi> kisileriGetir(){
        
        return kisiFacade.findAll();
        
    }
    
    public List<String> isimleriGetir(){
        
        return kisiFacade.isimleriGetir();
        
    }
    
}
