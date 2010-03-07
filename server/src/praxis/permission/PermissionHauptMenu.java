// @Generated(server/src/praxis/permission/PermissionHauptMenu.java)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.permission;

import de.ama.services.permission.PermissionSwitch;

public class PermissionHauptMenu extends de.ama.services.permission.PermissionContext { 

    public PermissionHauptMenu() {
        setContext("HauptMenu");
    }

//  @Generated("addSwitches")
    @Override
    protected void addSwitches(){

           add(new PermissionSwitch("LoginCommand (Anmelden)"));
           add(new PermissionSwitch("LogoutCommand (Abmelden)"));
           add(new PermissionSwitch("OpenListerCommand (Resourcen)"));
           add(new PermissionSwitch("OpenListerCommand (Personen)"));
           add(new PermissionSwitch("OpenListerCommand (TextBausteine)"));
           add(new PermissionSwitch("OpenListerCommand (EBM Elemete)"));
           add(new PermissionSwitch("OpenEditorCommand (Benutzer Berechtigungen)"));
           add(new PermissionSwitch("OpenListerCommand (Karteikarten)"));
           add(new PermissionSwitch("OpenEditorCommand (Neuen Karteikarte anlegen)"));
           add(new PermissionSwitch("OpenEditorCommand (Kalender pflegen)"));
    }


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}
