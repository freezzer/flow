/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package de.ama.services.permission.view {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.SaveBoAction;
import de.ama.framework.command.*;
import de.ama.framework.gui.frames.TreeEditor;
import de.ama.framework.util.*;
import de.ama.services.permission.PermissionContext;
import de.ama.services.permission.PermissionsData;

public class SavePermissionCommand  extends Command {
    private var permissionsData:PermissionsData;
    private var index:int;

    public function SavePermissionCommand(label:String="Berechtigung speichern", icon:String="save") {
       super(label,icon); 
    } 
      
    override protected function execute():void {
        var sa:SaveBoAction    = new SaveBoAction();
        var te:TreeEditor = TreeEditor(invoker);
        var pc:Object = te.selectedNode.data;

        if(pc is PermissionContext){
            permissionsData = PermissionsData(te.getData());
            index = permissionsData.contexts.indexOf(pc);
            sa.data = pc;
            ActionStarter.instance.execute(sa , new Callback(this, resulthandler ));
        }
    }

    private function resulthandler(action:SaveBoAction): void {
        permissionsData.contexts[index] = action.data;
        invoker.setData(permissionsData);
    }


    override public function isPermitted():Boolean {
        return true;
    }
}}
