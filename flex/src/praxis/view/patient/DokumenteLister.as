// @Generated(generated/flex/praxis/view/patient/DokumenteLister.as)
package praxis.view.patient {
import de.ama.framework.data.BusinessObject;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.services.Factory;
import de.ama.framework.command.*;
     
public class DokumenteLister extends ListPanel {

//  @Generated("createData")
     override public function createData():BusinessObject {
        return Factory.createBean("Dokument"); 
     } 

//  @Generated("addCommands")
     override public function addCommands():void {
        var cmd:Command;
        import praxis.view.patient.CreateDokumentCommand;
        cmd = new CreateDokumentCommand("neues Dokument","new");
        cmd.permissionId = "Patient:CreateDokumentCommand (neues Dokument)";
        cmd.setProperty("name","CreateDokumentCommand");
        addCommand(cmd);
        cmd = new DeleteNodeCommand("Dokument löschen","delete");
        cmd.permissionId = "Patient:DeleteNodeCommand (Dokument löschen)";
        addCommand(cmd);
     } 

//  @Generated("addCollumns")
     override public function addCollumns():void {
        addCollumn("String" ,"Typ" , "typ",false,false);
        addCollumn("String" ,"Name" , "name",false,false);
        addCollumn("String" ,"Pfad" , "url",false,false);
     } 


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}}

