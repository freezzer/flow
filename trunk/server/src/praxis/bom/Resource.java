// @Generated(generated/java/praxis/bom/Resource.java)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.bom;
import de.ama.framework.data.*;
import java.math.BigDecimal;

public class Resource  implements java.io.Serializable , de.ama.db.PersistentMarker { 


    private String name;
    public  String getName() { return de.ama.util.Util.saveToString(name); }
    public  void   setName(String in) {  name=in; }

    private String typ;
    public  String getTyp() { return de.ama.util.Util.saveToString(typ); }
    public  void   setTyp(String in) {  typ=in; }

    private String beschreibung;
    public  String getBeschreibung() { return de.ama.util.Util.saveToString(beschreibung); }
    public  void   setBeschreibung(String in) {  beschreibung=in; }

    private String farbe;
    public BigDecimal getFarbe() { return new BigDecimal(farbe); }
    public  void   setFarbe(BigDecimal  in) { farbe = in.toPlainString(); }

    private Person person;
    public  BoReference<Person> getPerson() { return new BoReference<Person>(person); }
    public  void setPerson(BoReference<Person> in) { person =in.getBo(); }




// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}
