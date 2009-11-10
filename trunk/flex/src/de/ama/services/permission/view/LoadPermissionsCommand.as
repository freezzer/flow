/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package de.ama.services.permission.view {
import de.ama.framework.util.*;
import de.ama.framework.command.*;
import de.ama.framework.data.*;
public class LoadPermissionsCommand  extends Command { 

    public function LoadPermissionsCommand(label:String="Berechtigungen laden", icon:String="refresh") {
       super(label,icon); 
    } 
      
    override protected function execute():void {
      
    } 
    

    override public function isPermitted():Boolean {
        return true;
    }
    
}}
