// @Generated(flex/src/praxis/view/person/PersonLister.as)
package praxis.view.person {
import de.ama.framework.data.BusinessObject;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.services.Factory;
import de.ama.framework.command.*;
     
public class PersonLister extends ListPanel {

//  @Generated("createData")
     override public function createData():BusinessObject {
        return Factory.createBean("Person"); 
     } 

//  @Generated("addCommands")
     override public function addCommands():void {
        var cmd:Command;
        cmd = new LoadTableCommand("Übersicht Personen neu laden","refresh");
        cmd.permissionId = "Person:LoadTableCommand (Übersicht Personen neu laden)";
        addCommand(cmd);
        cmd = new OpenEditorCommand("Person bearbeiten","edit");
        cmd.permissionId = "Person:OpenEditorCommand (Person bearbeiten)";
        cmd.setProperty("editor","PersonEditor");
        cmd.setProperty("default","true");
        addCommand(cmd);
        cmd = new DeleteBoCommand("Person löschen","delete");
        cmd.permissionId = "Person:DeleteBoCommand (Person löschen)";
        addCommand(cmd);
        cmd = new OpenEditorCommand_New("neue Person anlegen");
        cmd.permissionId = "Person:OpenEditorCommand_New (neue Person anlegen)";
        cmd.setProperty("editor","PersonEditor");
        addCommand(cmd);
        cmd = new OpenEditorCommand_Copy("Person kopieren");
        cmd.permissionId = "Person:OpenEditorCommand_Copy (Person kopieren)";
        cmd.setProperty("editor","PersonEditor");
        addCommand(cmd);
     } 

//  @Generated("addCollumns")
     override public function addCollumns():void {
        label = "Personen Auflister"
        addCollumn("String" ,"Vorname" , "vorname",false,true);
        addCollumn("String" ,"Nachname" , "nachname",false,true);
     } 


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}}

