// @Generated(server/src/praxis/bom/Person.java)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.bom;
import de.ama.framework.data.*;
import java.math.BigDecimal;

public class Person  implements java.io.Serializable , de.ama.db.PersistentMarker { 


    private String titel;
    public  String getTitel() { return de.ama.util.Util.saveToString(titel); }
    public  void   setTitel(String in) {  titel=in; }

    private String vorname;
    public  String getVorname() { return de.ama.util.Util.saveToString(vorname); }
    public  void   setVorname(String in) {  vorname=in; }

    private String nachname;
    public  String getNachname() { return de.ama.util.Util.saveToString(nachname); }
    public  void   setNachname(String in) {  nachname=in; }

    private String telefon;
    public  String getTelefon() { return de.ama.util.Util.saveToString(telefon); }
    public  void   setTelefon(String in) {  telefon=in; }

    private String mobil;
    public  String getMobil() { return de.ama.util.Util.saveToString(mobil); }
    public  void   setMobil(String in) {  mobil=in; }

    private String email;
    public  String getEmail() { return de.ama.util.Util.saveToString(email); }
    public  void   setEmail(String in) {  email=in; }

    private String notizen_TEXT;
    public  String getNotizen() { return de.ama.util.Util.saveToString(notizen_TEXT); }
    public  void   setNotizen(String in) {  notizen_TEXT=in; }


    private java.util.List<Adresse> adressen = new java.util.ArrayList<Adresse>();
    public  java.util.List<Adresse> getAdressen() { return adressen; }
    public  void   setAdressen(java.util.List<Adresse> in) {  adressen=in; }



// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}
