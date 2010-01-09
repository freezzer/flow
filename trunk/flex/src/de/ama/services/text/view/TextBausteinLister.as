package de.ama.services.text.view {
import de.ama.framework.data.BusinessObject;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.services.Factory;
import de.ama.framework.command.*;
     
public class TextBausteinLister extends ListPanel {
     
     override public function createData():BusinessObject {
        return Factory.createBean("TextBaustein"); 
     } 

     override public function addCommands():void {
        var cmd:Command;
        cmd = new LoadTableCommand("Textbausteine neu laden","refresh");
        cmd.permissionId = "Stammdaten:LoadTableCommand (Textbausteine neu laden)";
        addCommand(cmd);
        cmd = new OpenEditorCommand("Textbaustein bearbeiten","edit");
        cmd.permissionId = "Stammdaten:OpenEditorCommand (Textbaustein bearbeiten)";
        cmd.setProperty("editor","TextBausteinEditor");
        cmd.setProperty("default","true");
        addCommand(cmd);
        cmd = new DeleteBoCommand("Textbaustein loeschen","delete");
        cmd.permissionId = "Stammdaten:DeleteBoCommand (Textbaustein loeschen)";
        addCommand(cmd);
        cmd = new OpenEditorCommand_New("neuen Textbaustein anlegen");
        cmd.permissionId = "Stammdaten:OpenEditorCommand_New (neuen Textbaustein anlegen)";
        cmd.setProperty("editor","TextBausteinEditor");
        addCommand(cmd);
     } 

     override public function addCollumns():void {
        label = "Textbausteine"
        addCollumn("String" ,"Sequenz" , "key",false,true);
        addCollumn("String" ,"Text" , "text",false,true);
     } 
}}

