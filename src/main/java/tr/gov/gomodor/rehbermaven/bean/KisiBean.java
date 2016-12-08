package tr.gov.gomodor.rehbermaven.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;
import tr.gov.gomodor.rehbermaven.entity.Kisi;
import tr.gov.gomodor.rehbermaven.service.KisiService;
import tr.gov.gomodor.rehbermaven.util.JSFUtil;

@ManagedBean
@ViewScoped
public class KisiBean implements Serializable{
    
    private Kisi kisi = new Kisi();
    
    private List<Kisi> kisiListesi = new ArrayList<Kisi>();
    private List<String> isimListe = new ArrayList<String>();
    
    @EJB
    private KisiService kisiService;

    public KisiBean() {
        
    }
    
    @PostConstruct //const çalıştıktan sonra direk bu çalışacak demek.
    public void doldurKisiListe(){
        
        kisiListesi = kisiService.kisileriGetir();
        isimListe = kisiService.isimleriGetir();
        
    }

    public Kisi getKisi() {
        return kisi;
    }

    public void setKisi(Kisi kisi) {
        this.kisi = kisi;
    }
    
    public String ekle(){
        
        kisiService.ekle(kisi);
        JSFUtil.mesajGoster("Kişi eklendi", kisi.getAd()+" "+kisi.getSoyad()+" eklendi.");
        kisiListeGuncelle();
        
        return "kisiListele.xhtml?faces-redirect=true";
        
    }
    
    public void kisiListeGuncelle(){
        
        kisiListesi = kisiService.kisileriGetir();
        
    }
    
    public List<Kisi> getKisiListe(){
        
        return kisiListesi;
        
    }
    
    public void onRowEdit(RowEditEvent p_event){
        
        kisi = (Kisi) p_event.getObject();
        kisiService.guncelle(kisi);
        
        JSFUtil.mesajGoster("Kişi Güncellendi.", kisi.getAd() + " " + kisi.getSoyad() + " güncellendi.");
        
    }
    
    public void onRowCancel(RowEditEvent p_event){
        
        kisi = (Kisi) p_event.getObject();
        JSFUtil.mesajGoster("Kişi Güncellenmedi.", kisi.getAd() + " " + kisi.getSoyad() + " güncellenmedi.");
        
    }
    
    public List<String> tamamlaMethod(String p_sorgu){
        
        List<String> sonucListe = new ArrayList<String>();
        
        for (String isim : isimListe) {
            
            if(isim.toUpperCase(new Locale("tr","TR")).startsWith(p_sorgu.toUpperCase(new Locale("tr","TR")))){
                
                sonucListe.add(isim);
                
            }
            
        }
        
        return sonucListe;
        
    }
    
}
