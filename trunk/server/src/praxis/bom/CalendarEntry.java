// @Generated(generated/java/praxis/bom/CalendarEntry.java)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.bom;
import de.ama.framework.data.*;
import java.math.BigDecimal;

public class CalendarEntry  implements java.io.Serializable , de.ama.db.PersistentMarker { 


    private String date;
    public  String getDate() { return de.ama.util.Util.saveToString(date); }
    public  void   setDate(String in) {  date=in; }

    private int durationInMinutes;
    public  int getDurationInMinutes() {  return durationInMinutes; }
    public  void   setDurationInMinutes(int in) {  durationInMinutes=in; }

    private String time;
    public  String getTime() { return de.ama.util.Util.saveToString(time); }
    public  void   setTime(String in) {  time=in; }

    private String label;
    public  String getLabel() { return de.ama.util.Util.saveToString(label); }
    public  void   setLabel(String in) {  label=in; }

    private String text;
    public  String getText() { return de.ama.util.Util.saveToString(text); }
    public  void   setText(String in) {  text=in; }

    private Resource resource;
    public  BoReference<Resource> getResource() { return new BoReference<Resource>(resource); }
    public  void setResource(BoReference<Resource> in) { resource =in.getBo(); }




// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}
