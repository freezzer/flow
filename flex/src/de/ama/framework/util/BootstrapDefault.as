package de.ama.framework.util {
import de.ama.framework.command.CopyDataCommand;
import de.ama.framework.command.LoadTableCommand;
import de.ama.framework.command.OpenEditorCommand;
import de.ama.framework.command.OpenListerCommand;

public class BootstrapDefault {

    public function execute():void {
        Factory.registerCommand("LoadTableCommand", LoadTableCommand);
        Factory.registerCommand("OpenEditorCommand", OpenEditorCommand);
        Factory.registerCommand("OpenListerCommand", OpenListerCommand);
        Factory.registerCommand("CopyDataCommand", CopyDataCommand);
    }}
}