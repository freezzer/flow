// @Generated(generated/java/praxis/permission/PermissionPerson.java)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.permission;

import de.ama.services.permission.PermissionSwitch;

public class PermissionPerson extends de.ama.services.permission.PermissionContext { 

    public PermissionPerson() {
        setContext("Person");
    }

//  @Generated("addSwitches")
    @Override
    protected void addSwitches(){

           add(new PermissionSwitch("LoadTableCommand (Übersicht Personen neu laden)"));
           add(new PermissionSwitch("OpenEditorCommand (Person bearbeiten)"));
           add(new PermissionSwitch("DeleteBoCommand (Person löschen)"));
           add(new PermissionSwitch("OpenEditorCommand_New (neue Person anlegen)"));
           add(new PermissionSwitch("OpenEditorCommand_Copy (Person kopieren)"));
           add(new PermissionSwitch("CreateNodeCommand (neue Adresse)"));
           add(new PermissionSwitch("CreateNodeCommand (neue Adresse)"));
           add(new PermissionSwitch("CopyNodeCommand (Adresse kopieren)"));
           add(new PermissionSwitch("DeleteNodeCommand (Adresse löschen)"));
           add(new PermissionSwitch("SaveBoCommand (Person speichern)"));
    }


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}
