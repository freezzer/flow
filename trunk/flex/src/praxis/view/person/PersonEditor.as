// @Generated(generated/flex/praxis/view/person/PersonEditor.as)
package praxis.view.person {     
import de.ama.framework.gui.frames.*; 
import de.ama.framework.command.*; 
import de.ama.framework.data.BusinessObject;
import de.ama.services.Factory;
public class PersonEditor extends TreeEditor {
     
     override public function createData():BusinessObject {
        return Factory.createBean("Person"); 
     } 
     
     override public function addCommands():void {
        label = "Daten von {nachname} "
        permissionContext="Person";     

        var cmd:Command;
        cmd = new SaveBoCommand("Person speichern","save");
        cmd.permissionId = "Person:SaveBoCommand (Person speichern)";
        addCommand(cmd);
     } 

     override public function getPrototypeTree():TreeNode {
        var parent:TreeNode;
        var node:TreeNode;
        var root:TreeNode;
        var cmd:Command;


        node = new TreeNode(".", "Person", false, "user", "Person", "PersonPanel");
        node.defaultOpen=true;
        root = node;
        parent = node;

        node = new TreeNode("adressen", "Adressen", true, "table", "Adresse", "default");
        node.defaultOpen=true;
        parent.addTemplate(node);
        cmd = new CreateNodeCommand("neue Adresse","new");
        cmd.permissionId = "Person:CreateNodeCommand (neue Adresse)";
        node.addCommand(cmd);
        parent = node;

        node = new TreeNode("adressen", "Adresse {ort}", false, "form", "Adresse", "AdressePanel");
        node.defaultOpen=true;
        parent.addTemplate(node);
        cmd = new CreateNodeCommand("neue Adresse","new");
        cmd.permissionId = "Person:CreateNodeCommand (neue Adresse)";
        node.addCommand(cmd);
        cmd = new CopyNodeCommand("Adresse kopieren","copy");
        cmd.permissionId = "Person:CopyNodeCommand (Adresse kopieren)";
        node.addCommand(cmd);
        cmd = new DeleteNodeCommand("Adresse löschen","delete");
        cmd.permissionId = "Person:DeleteNodeCommand (Adresse löschen)";
        node.addCommand(cmd);

        parent = parent.parent; // end of default
        return root; 
     } 
   }
}

