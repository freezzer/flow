// @Generated(generated/flex/praxis/view/stammdaten/CodeElementPanel.as)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.view.stammdaten {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;
import de.ama.framework.data.*;
import de.ama.services.Factory;
public class CodeElementPanel  extends EditPanel { 

//  @Generated("CodeElementPanel")
    public function CodeElementPanel() {
        x=10;  y=10;
        setStyle("borderStyle","none");
        width=420;
        height=195;
    }

//  @Generated("addPanels")
     override public function addPanels():void {
        var panel:EditPanel;
     } 

//  @Generated("addFields")
     override public function addFields():void {
        var field:EditField;
 
        field = insertTextField("Code","code" ,10,20,100 ,300);
        field.editable=false;
 
        field = insertTextField("Kurztext","kurztext" ,10,50,100 ,300);
        field.editable=false;
 
        field = insertTextAreaField("Langtext","langtext" ,10,80,100 ,400,100);
        field.editable=false;
     } 


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}}
