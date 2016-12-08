package tr.gov.gomodor.rehbermaven.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import tr.gov.gomodor.rehbermaven.entity.Giris;
import tr.gov.gomodor.rehbermaven.entity.Log;
import tr.gov.gomodor.rehbermaven.facade.LogFacade;
import tr.gov.gomodor.rehbermaven.service.GirisService;
import tr.gov.gomodor.rehbermaven.util.JSFUtil;

@ManagedBean
@RequestScoped
public class GirisBean {

    private Giris giris = new Giris();
    
    private java.util.Date tarihsaat = new java.util.Date();

    @EJB
    private GirisService girisService;
    
    @EJB
    private LogFacade logFacade;

    public GirisBean() {
                
    }

    public Date getTarihsaat() {
        return tarihsaat;
    }

    public void setTarihsaat(Date tarihsaat) {
        this.tarihsaat = tarihsaat;
    }

    public Giris getGiris() {
        return giris;
    }

    public void setGiris(Giris giris) {
        this.giris = giris;
    }

    public String giriseYetkilimi() {

        boolean sonuc = girisService.giriseYetkilimi(giris);

        if (sonuc) {
            
            HttpSession session =JSFUtil.getSession();
            System.out.println(session.getId() + " nolu session başlıyor...");
            session.setAttribute("kullanici", giris.getKullanici());

            Log log = new Log();
            log.setKullanici(giris.getKullanici());
            log.setTarihsaat(new java.util.Date());
            log.setIslem("Giriş");
            
            logFacade.create(log);
            
            return "menu.xhtml?faces-redirect=true";

        } else {

            JSFUtil.hataGoster("Hatalı Giriş", "Kullanıcı Adı ya da Şifre Yanlış!");
            return "giris.xhtml?faces-redirect=true";

        }

    }
    
    public String guvenliCikis(){
        
        HttpSession session =JSFUtil.getSession();
        JSFUtil.sessionBitir(session);
        JSFUtil.mesajGoster("Session bitti.", "Yeniden Giriş Yapınız.");
        
        return "giris.xhtml?faces-redirect=true";
        
    }

    public void sistemTarihSaatGuncelle(){
        
        tarihsaat = new java.util.Date();
        
    }
                
    public String sistemTarihSaatGetir(){
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(tarihsaat);
        
    }
    
}
