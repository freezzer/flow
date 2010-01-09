/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/

package de.ama.services.text;
import de.ama.framework.data.*;

public class TextBaustein  implements java.io.Serializable , de.ama.db.PersistentMarker { 


    private String key;
    public  String getKey() { return de.ama.util.Util.saveToString(key); }
    public  void   setKey(String in) {  key=in; }

    private String text;
    public  String getText() { return de.ama.util.Util.saveToString(text); }
    public  void   setText(String in) {  text=in; }



}
