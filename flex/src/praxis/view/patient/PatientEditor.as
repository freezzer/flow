// @Generated(flex/src/praxis/view/patient/PatientEditor.as)
package praxis.view.patient {     
import de.ama.framework.gui.frames.*; 
import de.ama.framework.command.*; 
import de.ama.framework.data.BusinessObject;
import de.ama.services.Factory;
public class PatientEditor extends TreeEditor {
     
     override public function createData():BusinessObject {
        return Factory.createBean("Patient"); 
     } 
     
     override public function addCommands():void {
        label = "Daten von {nachname} "
        permissionContext="Patient";     

        var cmd:Command;
        cmd = new SaveBoCommand("Patient speichern","save");
        cmd.permissionId = "Patient:SaveBoCommand (Patient speichern)";
        addCommand(cmd);
     } 

     override public function getPrototypeTree():TreeNode {
        var parent:TreeNode;
        var node:TreeNode;
        var root:TreeNode;
        var cmd:Command;


        node = new TreeNode(".", "Patient", false, "user", "Patient", "PatientPanel");
        node.defaultOpen=true;
        root = node;
        parent = node;

        node = new TreeNode("eintraege", "Einträge", true, "table", "Eintrag", "default");
        node.defaultOpen=true;
        parent.addTemplate(node);
        cmd = new CreateNodeCommand("neuen Eintrag","new");
        cmd.permissionId = "Patient:CreateNodeCommand (neuen Eintrag)";
        node.addCommand(cmd);
        parent = node;

        node = new TreeNode("eintraege", "Eintrag {datum}", false, "form", "Eintrag", "EintragPanel");
        node.defaultOpen=true;
        parent.addTemplate(node);
        cmd = new CreateNodeCommand("neuer Eintrag","new");
        cmd.permissionId = "Patient:CreateNodeCommand (neuer Eintrag)";
        node.addCommand(cmd);
        cmd = new CopyNodeCommand("Eintrag kopieren","copy");
        cmd.permissionId = "Patient:CopyNodeCommand (Eintrag kopieren)";
        node.addCommand(cmd);
        cmd = new DeleteNodeCommand("Eintrag löschen","delete");
        cmd.permissionId = "Patient:DeleteNodeCommand (Eintrag löschen)";
        node.addCommand(cmd);

        parent = parent.parent; // end of default
        return root; 
     } 
   }
}

