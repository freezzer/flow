// @Generated(flex/src/praxis/view/patient/PatientPanel.as)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.view.patient {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;
import de.ama.framework.data.*;
import de.ama.services.Factory;
public class PatientPanel  extends EditPanel { 

//  @Generated("PatientPanel")
    public function PatientPanel() {
        x=10;  y=10;
        setStyle("borderStyle","none");
        width=700;
        height=385;
    }

//  @Generated("addPanels")
     override public function addPanels():void {
        var panel:EditPanel;
        addChild(Factory.createPanel("PatientKopfPanel"));
        panel=Factory.createPanel("AdressePanel");
        panel.label="Adresse";
        panel.x=334;
        panel.y=1;
        panel.width=355;
        panel.height=380;
        panel.path="adresse";
        panel.setStyle("borderStyle","solid");
        addChild(panel);
     } 

//  @Generated("addFields")
     override public function addFields():void {
        var field:EditField;
     } 


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}}
