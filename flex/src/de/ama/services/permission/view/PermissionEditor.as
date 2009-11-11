package de.ama.services.permission.view {
import de.ama.framework.gui.frames.*; 
import de.ama.framework.command.*; 
import de.ama.framework.data.Data;
import de.ama.services.Factory;
import de.ama.services.permission.PermissionService;
import de.ama.services.permission.PermissionsData;

public class PermissionEditor extends TreeEditor {



     override public function createData():Data {
        return PermissionService.instance.getPermissionsData();
     }
     
     override public function addCommands():void {
        label = "Berechtigung fuer {userName}"

        var cmd:Command;
        import de.ama.services.permission.view.SavePermissionCommand;
        cmd = new SavePermissionCommand("Berechtigung speichern","save");
        cmd.permissionId = "global: (Berechtigung speichern)";
        cmd.setProperty("name","SavePermissionCommand");
        addCommand(cmd);
     }

     override public function getPrototypeTree():TreeNode {
        var parent:TreeNode;
        var node:TreeNode;
        var root:TreeNode;
        var cmd:Command;


        node = new TreeNode("contexts", "Berechtigungen", true, "wrench", "PermissionsData", "default");
        node.defaultOpen=true;
        root = node;
        parent = node;

        node = new TreeNode("contexts", "Bereich {context}", false, "user", "PermissionContext", "PermissionContextPanel");
        node.defaultOpen=true;
        parent.addTemplate(node);

        return root;
     } 
   }
}

