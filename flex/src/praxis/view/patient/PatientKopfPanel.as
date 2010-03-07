// @Generated(flex/src/praxis/view/patient/PatientKopfPanel.as)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.view.patient {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;
import de.ama.framework.data.*;
import de.ama.services.Factory;
public class PatientKopfPanel  extends EditPanel { 

//  @Generated("PatientKopfPanel")
    public function PatientKopfPanel() {
        x=0;  y=0;
        setStyle("borderStyle","solid");
        width=315;
        height=383;
        label="Patientendaten";
        path=".";
    }

//  @Generated("addPanels")
     override public function addPanels():void {
        var panel:EditPanel;
     } 

//  @Generated("addFields")
     override public function addFields():void {
        var field:EditField;
 
        field = insertTextField("Vorname","vorname" ,10,65,100 ,300);
        field.editable=false;
 
        field = insertTextField("Nachname","nachname" ,10,95,100 ,300);
        field.editable=false;
 
        field = insertTextField("Telefon","telefon" ,10,313,100 ,300);
        field.editable=false;
 
        field = insertTextField("Email","email" ,10,279,100 ,300);
        field.editable=false;
 
        field = insertTextField("Status","status" ,10,154,100 ,300);
        field.editable=false;
 
        field = insertTextField("Kasse","kasse" ,10,185,100 ,300);
        field.editable=false;
 
        field = insertTextField("Beruf","beruf" ,10,218,100 ,300);
        field.editable=false;
 
        field = insertTextField("Geschlecht","geschlecht" ,10,125,100 ,300);
        field.editable=false;
 
        field = insertDateField("Erfasst am","erfasst" ,8,16,100 ,300);
        field.editable=false;
     } 


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}}
