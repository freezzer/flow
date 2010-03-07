// @Generated(flex/src/praxis/view/person/AdressePanel.as)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.view.person {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;
import de.ama.framework.data.*;
import de.ama.services.Factory;
public class AdressePanel  extends EditPanel { 

//  @Generated("AdressePanel")
    public function AdressePanel() {
        x=10;  y=10;
        setStyle("borderStyle","solid");
        width=350;
        height=360;
    }

//  @Generated("addPanels")
     override public function addPanels():void {
        var panel:EditPanel;
        var map:GoogleMap = GoogleMap(addChild(new GoogleMap(10, 147, 325, 205)));
        map.countryField=getEditField("land");
        map.cityField=getEditField("ort");
        map.zipField=getEditField("plz");
        map.streetField=getEditField("strasse");
        map.zoomControl=false;
        map.typeControl=false;
     } 

//  @Generated("addFields")
     override public function addFields():void {
        var field:EditField;
 
        field = insertTextField("Land","land" ,10,12,100 ,300);
        field.editable=false;
        field.defaultValue="Deutschland";
 
        field = insertTextField("Ort","ort" ,10,41,100 ,300);
        field.editable=false;
 
        field = insertTextField("Plz","plz" ,10,71,100 ,300);
        field.editable=false;
 
        field = insertTextField("Strasse","strasse" ,10,101,100 ,300);
        field.editable=false;
     } 


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************

    override public function set label(value:String):void {
        super.label = value;
    }

// [MANUAL.CODE.END] 

}}
