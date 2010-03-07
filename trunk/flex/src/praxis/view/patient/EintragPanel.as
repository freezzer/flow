// @Generated(flex/src/praxis/view/patient/EintragPanel.as)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.view.patient {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;
import de.ama.framework.data.*;
import de.ama.services.Factory;
public class EintragPanel  extends EditPanel { 

//  @Generated("EintragPanel")
    public function EintragPanel() {
        x=10;  y=10;
        setStyle("borderStyle","solid");
        width=650;
        height=600;
        path=".";
    }

//  @Generated("addPanels")
     override public function addPanels():void {
        var panel:EditPanel;
     } 

//  @Generated("addFields")
     override public function addFields():void {
        var field:EditField;
 
        field = insertTextField("Vorname","#vorname" ,18,13,90 ,300);
        field.editable=false;
 
        field = insertTextField("Nachname","#nachname" ,18,41,90 ,300);
        field.editable=false;
 
        field = insertTextField("Status","#status" ,330,42,60 ,300);
        field.editable=false;
 
        field = insertTextField("Kasse","#kasse" ,330,12,60 ,300);
        field.editable=false;
 
        field = insertTextField("Erfasser","erfasser" ,330,102,60 ,300);
        field.editable=false;
 
        field = insertDateField("Datum","datum" ,23,102,90 ,210);
        field.editable=false;
 
        field = insertTextAreaField("Anamnese","anamnese" ,18,166,90 ,615,90);
        field.editable=false;
 
        field = insertTextAreaField("Befund","befund" ,18,262,90 ,615,90);
        field.editable=false;
 
        field = insertTextAreaField("Therapie","therapie" ,18,358,90 ,615,90);
        field.editable=false;
 
        field = insertListField("Dokumente","dokumente","DokumenteLister" ,637,102 ,375,345);
        field.editable=false;
     } 


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}}
