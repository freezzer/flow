// @Generated(generated/java/praxis/permission/PermissionStammdaten.java)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.permission;

import de.ama.services.permission.PermissionSwitch;

public class PermissionStammdaten extends de.ama.services.permission.PermissionContext { 

    public PermissionStammdaten() {
        setContext("Stammdaten");
    }

//  @Generated("addSwitches")
    @Override
    protected void addSwitches(){

           add(new PermissionSwitch("LoadTableCommand (Code Elemente neu laden)"));
           add(new PermissionSwitch("OpenEditorCommand (Code Element bearbeiten)"));
           add(new PermissionSwitch("DeleteBoCommand (CodeElement löschen)"));
           add(new PermissionSwitch("OpenEditorCommand_New (neues Code Element anlegen)"));
           add(new PermissionSwitch("LoadTableCommand (Resource neu laden)"));
           add(new PermissionSwitch("OpenEditorCommand (Resource bearbeiten)"));
           add(new PermissionSwitch("DeleteBoCommand (Resource löschen)"));
           add(new PermissionSwitch("OpenEditorCommand_New (neue Resource anlegen)"));
           add(new PermissionSwitch("SaveBoCommand (Code-Element speichern)"));
           add(new PermissionSwitch("SaveBoCommand (Resource speichern)"));
    }


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}
