// @Generated(flex/src/praxis/view/stammdaten/ResourceEditor.as)
package praxis.view.stammdaten {     
import de.ama.framework.gui.frames.*; 
import de.ama.framework.command.*; 
import de.ama.framework.data.BusinessObject;
import de.ama.services.Factory;
public class ResourceEditor extends PanelEditor {

//  @Generated("createData")
     override public function createData():BusinessObject {
       return Factory.createBean("Resource"); 
     } 
     

//  @Generated("addCommands")
     override public function addCommands():void {
        label = "Resource {name} "
   permissionContext="Stammdaten";     

        var cmd:Command;
        cmd = new SaveBoCommand("Resource speichern","save");
        cmd.permissionId = "Stammdaten:SaveBoCommand (Resource speichern)";
        addCommand(cmd);
     } 

//  @Generated("addPanels")
     override public function addPanels():void {
        var panel:EditPanel;
        addChild(Factory.createPanel("ResourcePanel"));
     } 


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

   }
}

