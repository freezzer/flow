/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package comp.view.person {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;

public class AdressePanel  extends EditPanel {

    private var map:GoogleMap = null;

    public function AdressePanel() {
        x=0;  y=0;
    }
   
     override public function addPanels():void {
        var panel:EditPanel;

        var map:GoogleMap = GoogleMap(addChild(GoogleMap.create(10, 120, 650, 400)));
        map.countryField=getEditField("land"); 
        map.cityField=getEditField("ort");
        map.streetField=getEditField("strasse");
        map.zipField=getEditField("plz");

     }

     override public function addFields():void {
        var field:EditField;
 
        field = insertTextField("Ort","ort",10,20,85,275);
        field = insertTextField("Plz","plz",321,20,85,300);
        field = insertTextField("Strasse","strasse",10,53,85,275);
        field = insertTextField("Land","land",10,86,85,275);
        field.defaultValue="Deutschland"; 

        field = insertTextField("Hausnummer","hausnummer",321,50,85,300);
     }



}}
