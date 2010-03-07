// @Generated(flex/src/praxis/view/stammdaten/CodeElementEditor.as)
package praxis.view.stammdaten {     
import de.ama.framework.gui.frames.*; 
import de.ama.framework.command.*; 
import de.ama.framework.data.BusinessObject;
import de.ama.services.Factory;
public class CodeElementEditor extends PanelEditor {

//  @Generated("createData")
     override public function createData():BusinessObject {
       return Factory.createBean("CodeElement"); 
     } 
     

//  @Generated("addCommands")
     override public function addCommands():void {
        label = "Code-Element {code} "
   permissionContext="Stammdaten";     

        var cmd:Command;
        cmd = new SaveBoCommand("Code-Element speichern","save");
        cmd.permissionId = "Stammdaten:SaveBoCommand (Code-Element speichern)";
        addCommand(cmd);
     } 

//  @Generated("addPanels")
     override public function addPanels():void {
        var panel:EditPanel;
        addChild(Factory.createPanel("CodeElementPanel"));
     } 


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

   }
}

