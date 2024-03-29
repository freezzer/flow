/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package de.ama.services.permission.view {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.SaveBoAction;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.framework.util.*;
import de.ama.framework.command.*;
import de.ama.framework.data.*;
import de.ama.services.permission.PermissionContext;
import de.ama.services.permission.PermissionSwitch;

public class SelectNoneCommand  extends Command {

    public function SelectNoneCommand(label:String="alle abwaehlen", icon:String="cancel") {
       super(label,icon); 
    } 
      
    override protected function execute():void {
        var lp:ListPanel = ListPanel(invoker);
        var array:Array = lp.getDataTable();
        for each(var s:PermissionSwitch in array) {
           s.isOn = false;
        }
        lp.refreshGui();
    }

    override public function isPermitted():Boolean {
        return true;
    }

}}
