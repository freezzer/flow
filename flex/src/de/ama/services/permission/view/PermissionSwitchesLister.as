package de.ama.services.permission.view {
import de.ama.framework.data.BusinessObject;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.services.Factory;
import de.ama.framework.command.*;
     
public class PermissionSwitchesLister extends ListPanel {
     
     override public function createData():BusinessObject {
        return Factory.createBean("default"); 
     } 

     override public function addCommands():void {
        var cmd:Command;
     } 

     override public function addCollumns():void {
        addCollumn("String","Name" , "key",false);
        addCollumn("Bolean","An/Aus" , "isOn",true,25);
     } 
}}

