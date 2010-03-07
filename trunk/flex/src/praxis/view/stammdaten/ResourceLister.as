// @Generated(flex/src/praxis/view/stammdaten/ResourceLister.as)
package praxis.view.stammdaten {
import de.ama.framework.data.BusinessObject;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.services.Factory;
import de.ama.framework.command.*;
     
public class ResourceLister extends ListPanel {

//  @Generated("createData")
     override public function createData():BusinessObject {
        return Factory.createBean("Resource"); 
     } 

//  @Generated("addCommands")
     override public function addCommands():void {
        var cmd:Command;
        cmd = new LoadTableCommand("Resource neu laden","refresh");
        cmd.permissionId = "Stammdaten:LoadTableCommand (Resource neu laden)";
        addCommand(cmd);
        cmd = new OpenEditorCommand("Resource bearbeiten","edit");
        cmd.permissionId = "Stammdaten:OpenEditorCommand (Resource bearbeiten)";
        cmd.setProperty("editor","ResourceEditor");
        cmd.setProperty("default","true");
        addCommand(cmd);
        cmd = new DeleteBoCommand("Resource löschen","delete");
        cmd.permissionId = "Stammdaten:DeleteBoCommand (Resource löschen)";
        addCommand(cmd);
        cmd = new OpenEditorCommand_New("neue Resource anlegen");
        cmd.permissionId = "Stammdaten:OpenEditorCommand_New (neue Resource anlegen)";
        cmd.setProperty("editor","ResourceEditor");
        addCommand(cmd);
     } 

//  @Generated("addCollumns")
     override public function addCollumns():void {
        label = "Resourcen"
        addCollumn("String" ,"Name" , "name",false,true);
        addCollumn("String" ,"Typ" , "typ",false,true);
        addCollumn("String" ,"Beschreibung" , "beschreibung",false,true);
     } 


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}}

