// @Generated(flex/src/praxis/view/patient/PatientLister.as)
package praxis.view.patient {
import de.ama.framework.data.BusinessObject;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.services.Factory;
import de.ama.framework.command.*;
     
public class PatientLister extends ListPanel {

//  @Generated("createData")
     override public function createData():BusinessObject {
        return Factory.createBean("Patient"); 
     } 

//  @Generated("addCommands")
     override public function addCommands():void {
        var cmd:Command;
        cmd = new LoadTableCommand("Übersicht Patienten neu laden","refresh");
        cmd.permissionId = "Patient:LoadTableCommand (Übersicht Patienten neu laden)";
        addCommand(cmd);
        cmd = new OpenEditorCommand("Patient bearbeiten","edit");
        cmd.permissionId = "Patient:OpenEditorCommand (Patient bearbeiten)";
        cmd.setProperty("editor","PatientEditor");
        cmd.setProperty("default","true");
        addCommand(cmd);
        cmd = new DeleteBoCommand("Patient löschen","delete");
        cmd.permissionId = "Patient:DeleteBoCommand (Patient löschen)";
        addCommand(cmd);
        cmd = new OpenEditorCommand_New("neue Patient anlegen");
        cmd.permissionId = "Patient:OpenEditorCommand_New (neue Patient anlegen)";
        cmd.setProperty("editor","PatientEditor");
        addCommand(cmd);
        cmd = new OpenEditorCommand_Copy("Patient kopieren");
        cmd.permissionId = "Patient:OpenEditorCommand_Copy (Patient kopieren)";
        cmd.setProperty("editor","PatientEditor");
        addCommand(cmd);
     } 

//  @Generated("addCollumns")
     override public function addCollumns():void {
        label = "Patienten Auflister"
        addCollumn("String" ,"Vorname" , "vorname",false,true);
        addCollumn("String" ,"Nachname" , "nachname",false,true);
        addCollumn("String" ,"Geschlecht" , "geschlecht",false,true);
        addCollumn("String" ,"Kasse" , "kasse",false,true);
     } 


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}}

