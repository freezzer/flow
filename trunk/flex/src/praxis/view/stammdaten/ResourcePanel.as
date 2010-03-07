// @Generated(generated/flex/praxis/view/stammdaten/ResourcePanel.as)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.view.stammdaten {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;
import de.ama.framework.data.*;
import de.ama.services.Factory;
public class ResourcePanel  extends EditPanel { 

//  @Generated("ResourcePanel")
    public function ResourcePanel() {
        x=10;  y=10;
        setStyle("borderStyle","none");
        width=450;
        height=285;
    }

//  @Generated("addPanels")
     override public function addPanels():void {
        var panel:EditPanel;
     } 

//  @Generated("addFields")
     override public function addFields():void {
        var field:EditField;
 
        field = insertTextField("Name","name" ,10,20,100 ,300);
        field.editable=false;
 
        field = insertTextField("Typ","typ" ,10,50,100 ,300);
        field.editable=false;
 
        field = insertTextField("Farbe","farbe" ,319,80,45 ,115);
        field.editable=false;
 
        field = insertTextAreaField("Beschreibung","beschreibung" ,9,114,100 ,425,135);
        field.editable=false;
 
        field = insertProxyField("Person","nachname","Person","person" ,10,80,100 ,300);
        ProxyField(field).editor = "PersonEditor";
        ProxyField(field).lister = "PersonLister";
     } 


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}}
