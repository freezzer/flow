/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package comp.view.person {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;
import de.ama.framework.data.*;
import de.ama.framework.util.Callback;
import de.ama.services.Factory;
public class AdressePanel  extends EditPanel {

    var map:GoogleMap = null;

    public function AdressePanel() {
        x=0;  y=0;
    }
   
     override public function addPanels():void {
        var panel:EditPanel;
        map = new GoogleMap();
        map.x=10;
        map.y=120;
        map.height=400;
        map.width=650;
        map.callback = new Callback(this,onMapMoved);
        addChild(map);
     }

     override public function addFields():void {
        var field:EditField;
 
        field = insertTextField("Ort","ort",10,20,85,275);
        field.changeCallback=new Callback(this,moveMap);
 
        field = insertTextField("Plz","plz",321,20,85,300);
 
        field = insertTextField("Strasse","strasse",10,53,85,275);
        field.changeCallback=new Callback(this,moveMap);
 
        field = insertTextField("Land","land",10,86,85,275);
        field.defaultValue="Deutschland"; 
        field.changeCallback=new Callback(this,moveMap) ;
 
        field = insertTextField("Hausnummer","hausnummer",321,50,85,300);
     }

    private function moveMap(field:EditField):void {
        map.moveToLocation(getEditField("land").getInputText() ,
                getEditField("ort").getInputText() ,
                getEditField("strasse").getInputText());
    }

    private function onMapMoved():void {
        getEditField("land").setInputText(map.country);
        getEditField("strasse").setInputText(map.street);
        getEditField("ort").setInputText(map.city);
    }
   

}}
