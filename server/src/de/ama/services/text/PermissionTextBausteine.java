/*

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow

*/

package de.ama.services.text;

import de.ama.services.permission.PermissionSwitch;

public class PermissionTextBausteine extends de.ama.services.permission.PermissionContext { 

    public PermissionTextBausteine() {
        setContext("TextBausteine");
    }

    @Override
    protected void addSwitches(){

           add(new PermissionSwitch("LoadTableCommand (Textbausteine neu laden)"));
           add(new PermissionSwitch("OpenEditorCommand (Textbaustein bearbeiten)"));
           add(new PermissionSwitch("DeleteBoCommand (Textbaustein loeschen)"));
           add(new PermissionSwitch("OpenEditorCommand_New (neuen Textbaustein anlegen)"));
           add(new PermissionSwitch("SaveBoCommand (Textbaustein speichern)"));
           add(new PermissionSwitch("LoadBoCommand (Textbaustein laden)"));
           add(new PermissionSwitch("DeleteBoCommand (Textbaustein loeschen)"));
    }

}