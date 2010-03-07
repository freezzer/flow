// @Generated(flex/src/praxis/view/person/PersonPanel.as)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.view.person {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;
import de.ama.framework.data.*;
import de.ama.services.Factory;
public class PersonPanel  extends EditPanel { 

//  @Generated("PersonPanel")
    public function PersonPanel() {
        x=10;  y=10;
        setStyle("borderStyle","solid");
        width=350;
        height=300;
    }

//  @Generated("addPanels")
     override public function addPanels():void {
        var panel:EditPanel;
     } 

//  @Generated("addFields")
     override public function addFields():void {
        var field:EditField;
 
        field = insertTextField("Titel","titel" ,10,15,100 ,190);
        field.editable=false;
 
        field = insertTextField("Vorname","vorname" ,10,45,100 ,300);
        field.editable=false;
 
        field = insertTextField("Nachname","nachname" ,10,75,100 ,300);
        field.editable=false;
 
        field = insertTextField("Telefon","telefon" ,10,105,100 ,300);
        field.editable=false;
 
        field = insertTextField("Mobil","mobil" ,10,135,100 ,300);
        field.editable=false;
 
        field = insertTextField("E-Mail","email" ,10,165,100 ,300);
        field.editable=false;
 
        field = insertTextAreaField("Notizen","notizen" ,10,195,100 ,405,100);
        field.editable=false;
     } 


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}}
