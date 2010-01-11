package de.ama.services.text.view {
import de.ama.framework.gui.frames.*; 
import de.ama.framework.command.*; 
import de.ama.framework.data.BusinessObject;
import de.ama.services.Factory;
public class TextBausteinEditor extends PanelEditor {
     
     override public function createData():BusinessObject {
       return Factory.createBean("TextBaustein"); 
     } 
     
     override public function addCommands():void {
        label = "Textbaustein {key} "
   permissionContext="TextBausteine";

        var cmd:Command;
        cmd = new SaveBoCommand("Textbaustein speichern","save");
        cmd.permissionId = "TextBausteine:SaveBoCommand (Textbaustein speichern)";
        addCommand(cmd);
        cmd = new LoadBoCommand("Textbaustein laden","refresh");
        cmd.permissionId = "TextBausteine:LoadBoCommand (Textbaustein laden)";
        addCommand(cmd);
        cmd = new DeleteBoCommand("Textbaustein loeschen","delete");
        cmd.permissionId = "TextBausteine:DeleteBoCommand (Textbaustein loeschen)";
        addCommand(cmd);
     } 

     override public function addPanels():void {
        var panel:EditPanel;
        addChild(Factory.createPanel("TextBausteinPanel"));
     } 

   }
}

