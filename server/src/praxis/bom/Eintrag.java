// @Generated(generated/java/praxis/bom/Eintrag.java)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.bom;
import de.ama.framework.data.*;
import java.math.BigDecimal;

public class Eintrag  implements java.io.Serializable , de.ama.db.PersistentMarker { 


    private String erfasser;
    public  String getErfasser() { return de.ama.util.Util.saveToString(erfasser); }
    public  void   setErfasser(String in) {  erfasser=in; }

    private String datum;
    public  String getDatum() { return de.ama.util.Util.saveToString(datum); }
    public  void   setDatum(String in) {  datum=in; }

    private String anamnese_TEXT;
    public  String getAnamnese() { return de.ama.util.Util.saveToString(anamnese_TEXT); }
    public  void   setAnamnese(String in) {  anamnese_TEXT=in; }

    private String befund_TEXT;
    public  String getBefund() { return de.ama.util.Util.saveToString(befund_TEXT); }
    public  void   setBefund(String in) {  befund_TEXT=in; }

    private String therapie_TEXT;
    public  String getTherapie() { return de.ama.util.Util.saveToString(therapie_TEXT); }
    public  void   setTherapie(String in) {  therapie_TEXT=in; }


    private java.util.List<Dokument> dokumente = new java.util.ArrayList<Dokument>();
    public  java.util.List<Dokument> getDokumente() { return dokumente; }
    public  void   setDokumente(java.util.List<Dokument> in) {  dokumente=in; }



// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}
