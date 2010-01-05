package de.ama.services {
import de.ama.framework.command.LoadTableCommand;
import de.ama.framework.command.OpenEditorCommand;
import de.ama.framework.command.OpenListerCommand;
import de.ama.framework.command.OpenNewEditorCommand;
import de.ama.framework.command.SelectBoCommand;
import de.ama.framework.data.BoReference;
import de.ama.framework.data.LookupDataProvider;
import de.ama.framework.data.ModelDataProvider;
import de.ama.framework.util.Condition;
import de.ama.framework.util.Query;
import de.ama.services.permission.PermissionContext;
import de.ama.services.permission.PermissionSwitch;
import de.ama.services.permission.PermissionsData;
import de.ama.services.permission.view.PermissionContextPanel;
import de.ama.services.permission.view.PermissionEditor;
import de.ama.services.permission.view.PermissionSwitchesLister;

public class BootstrapDefault {

    public function execute():void {

        Factory.registerBean("Condition", Condition);
        Factory.registerBean("Query", Query);

        Factory.registerBean("PermissionsData", PermissionsData);
        Factory.registerBean("PermissionContext", PermissionContext);
        Factory.registerBean("PermissionSwitch", PermissionSwitch);
        Factory.registerEditor("PermissionEditor", PermissionEditor);
        Factory.registerPanel("PermissionContextPanel", PermissionContextPanel);
        Factory.registerLister("PermissionSwitchesLister", PermissionSwitchesLister);

        Factory.registerCommand("SelectBoCommand", SelectBoCommand);
        Factory.registerCommand("LoadTableCommand", LoadTableCommand);
        Factory.registerCommand("OpenNewEditorCommand", OpenNewEditorCommand);
        Factory.registerCommand("OpenEditorCommand", OpenEditorCommand);
        Factory.registerCommand("OpenListerCommand", OpenListerCommand);

        Factory.registerCommand("BoReference", BoReference);
        Factory.registerProvider("LookupDataProvider", LookupDataProvider);
        Factory.registerProvider("ModelDataProvider", ModelDataProvider);


    }}
}