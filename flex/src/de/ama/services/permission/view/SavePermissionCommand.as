/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package de.ama.services.permission.view {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.SaveBoAction;
import de.ama.framework.util.*;
import de.ama.framework.command.*;
import de.ama.framework.data.*;
public class SavePermissionCommand  extends Command { 

    public function SavePermissionCommand(label:String="Berechtigung speichern", icon:String="save") {
       super(label,icon); 
    } 
      
    override protected function execute():void {
        var sa:SaveBoAction    = new SaveBoAction();
        sa.data = invoker.getData();
        ActionStarter.instance.execute(sa , new Callback(this, resulthandler ));
    }

    private function resulthandler(action:SaveBoAction): void {
        invoker.setData(Data(action.data));
    }

}}
