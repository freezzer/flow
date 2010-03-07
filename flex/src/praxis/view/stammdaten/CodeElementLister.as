// @Generated(flex/src/praxis/view/stammdaten/CodeElementLister.as)
package praxis.view.stammdaten {
import de.ama.framework.data.BusinessObject;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.services.Factory;
import de.ama.framework.command.*;
     
public class CodeElementLister extends ListPanel {

//  @Generated("createData")
     override public function createData():BusinessObject {
        return Factory.createBean("CodeElement"); 
     } 

//  @Generated("addCommands")
     override public function addCommands():void {
        var cmd:Command;
        cmd = new LoadTableCommand("Code Elemente neu laden","refresh");
        cmd.permissionId = "Stammdaten:LoadTableCommand (Code Elemente neu laden)";
        addCommand(cmd);
        cmd = new OpenEditorCommand("Code Element bearbeiten","edit");
        cmd.permissionId = "Stammdaten:OpenEditorCommand (Code Element bearbeiten)";
        cmd.setProperty("editor","CodeElementEditor");
        cmd.setProperty("default","true");
        addCommand(cmd);
        cmd = new DeleteBoCommand("CodeElement löschen","delete");
        cmd.permissionId = "Stammdaten:DeleteBoCommand (CodeElement löschen)";
        addCommand(cmd);
        cmd = new OpenEditorCommand_New("neues Code Element anlegen");
        cmd.permissionId = "Stammdaten:OpenEditorCommand_New (neues Code Element anlegen)";
        cmd.setProperty("editor","CodeElementEditor");
        addCommand(cmd);
     } 

//  @Generated("addCollumns")
     override public function addCollumns():void {
        label = "Code Elemente"
        addCollumn("String" ,"Code" , "code",false,true);
        addCollumn("String" ,"Kurztext" , "kurztext",false,true);
        addCollumn("String" ,"Langtext" , "langtext",false,true);
     } 


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}}

