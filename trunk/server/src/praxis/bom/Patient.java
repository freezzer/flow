// @Generated(generated/java/praxis/bom/Patient.java)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.bom;
import de.ama.framework.data.*;
import java.math.BigDecimal;

public class Patient  implements java.io.Serializable , de.ama.db.PersistentMarker { 


    private String vorname;
    public  String getVorname() { return de.ama.util.Util.saveToString(vorname); }
    public  void   setVorname(String in) {  vorname=in; }

    private String nachname;
    public  String getNachname() { return de.ama.util.Util.saveToString(nachname); }
    public  void   setNachname(String in) {  nachname=in; }

    private String telefon;
    public  String getTelefon() { return de.ama.util.Util.saveToString(telefon); }
    public  void   setTelefon(String in) {  telefon=in; }

    private String email;
    public  String getEmail() { return de.ama.util.Util.saveToString(email); }
    public  void   setEmail(String in) {  email=in; }

    private String status;
    public  String getStatus() { return de.ama.util.Util.saveToString(status); }
    public  void   setStatus(String in) {  status=in; }

    private String kasse;
    public  String getKasse() { return de.ama.util.Util.saveToString(kasse); }
    public  void   setKasse(String in) {  kasse=in; }

    private String beruf;
    public  String getBeruf() { return de.ama.util.Util.saveToString(beruf); }
    public  void   setBeruf(String in) {  beruf=in; }

    private String geschlecht;
    public  String getGeschlecht() { return de.ama.util.Util.saveToString(geschlecht); }
    public  void   setGeschlecht(String in) {  geschlecht=in; }

    private String erfasst;
    public  String getErfasst() { return de.ama.util.Util.saveToString(erfasst); }
    public  void   setErfasst(String in) {  erfasst=in; }

    private Adresse adresse;
    public  Adresse getAdresse() { if(adresse==null){ adresse=new Adresse(); } return adresse; }
    public  void   setAdresse(Adresse in) {  adresse=in; }


    private java.util.List<Eintrag> eintraege = new java.util.ArrayList<Eintrag>();
    public  java.util.List<Eintrag> getEintraege() { return eintraege; }
    public  void   setEintraege(java.util.List<Eintrag> in) {  eintraege=in; }



// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}
