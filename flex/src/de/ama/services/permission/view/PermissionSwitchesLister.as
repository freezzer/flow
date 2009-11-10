package de.ama.services.permission.view {
import de.ama.framework.data.Data;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.services.Factory;
import de.ama.framework.command.*;
     
public class PermissionSwitchesLister extends ListPanel {
     
     override public function createData():Data {
        return Factory.createBean("default"); 
     } 

     override public function addCommands():void {
        var cmd:Command;
     } 

     override public function addCollumns():void {
        addCollumn("Name" , "key",false);
        addCollumn("An/Aus" , "isOn",true,25);
     } 
}}

