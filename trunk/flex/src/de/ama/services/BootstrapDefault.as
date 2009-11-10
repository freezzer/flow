package de.ama.services {
import de.ama.framework.util.*;
import de.ama.framework.command.CopyDataCommand;
import de.ama.framework.command.LoadTableCommand;
import de.ama.framework.command.OpenEditorCommand;
import de.ama.framework.command.OpenListerCommand;
import de.ama.services.permission.PermissionContext;
import de.ama.services.permission.PermissionSwitch;
import de.ama.services.permission.PermissionsData;
import de.ama.services.permission.view.PermissionContextPanel;
import de.ama.services.permission.view.PermissionEditor;
import de.ama.services.permission.view.PermissionSwitchesLister;

public class BootstrapDefault {

    public function execute():void {

        Factory.registerBean("PermissionsData", PermissionsData);
        Factory.registerBean("PermissionContext", PermissionContext);
        Factory.registerBean("PermissionSwitch", PermissionSwitch);
        Factory.registerEditor("PermissionEditor", PermissionEditor);
        Factory.registerPanel("PermissionContextPanel", PermissionContextPanel);
        Factory.registerLister("PermissionSwitchesLister", PermissionSwitchesLister);

        Factory.registerCommand("LoadTableCommand", LoadTableCommand);
        Factory.registerCommand("OpenEditorCommand", OpenEditorCommand);
        Factory.registerCommand("OpenListerCommand", OpenListerCommand);


    }}
}