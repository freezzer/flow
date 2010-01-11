package de.ama.services {
import de.ama.framework.command.LoadTableCommand;
import de.ama.framework.command.LoginCommand;
import de.ama.framework.command.OpenEditorCommand;
import de.ama.framework.command.OpenEditorCommand_New;
import de.ama.framework.command.OpenListerCommand;
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
import de.ama.services.text.TextBaustein;
import de.ama.services.text.view.PermissionTextBausteine;
import de.ama.services.text.view.TextBausteinEditor;
import de.ama.services.text.view.TextBausteinLister;
import de.ama.services.text.view.TextBausteinPanel;

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
        Factory.registerCommand("OpenNewEditorCommand", OpenEditorCommand_New);
        Factory.registerCommand("OpenEditorCommand", OpenEditorCommand);
        Factory.registerCommand("OpenListerCommand", OpenListerCommand);
        Factory.registerCommand("LoginCommand", LoginCommand);

        Factory.registerBean("BoReference", BoReference);
        Factory.registerProvider("LookupDataProvider", LookupDataProvider);
        Factory.registerProvider("ModelDataProvider", ModelDataProvider);


        Factory.registerBean("TextBaustein", TextBaustein);
        Factory.registerPanel("TextBausteinPanel", TextBausteinPanel);
        Factory.registerEditor("TextBausteinEditor", TextBausteinEditor);
        Factory.registerLister("TextBausteinLister", TextBausteinLister);
        Factory.registerPermission("PermissionTextBausteine", PermissionTextBausteine);


    }}
}