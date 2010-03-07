// @Generated(server/src/praxis/permission/PermissionPatient.java)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.permission;

import de.ama.services.permission.PermissionSwitch;

public class PermissionPatient extends de.ama.services.permission.PermissionContext { 

    public PermissionPatient() {
        setContext("Patient");
    }

//  @Generated("addSwitches")
    @Override
    protected void addSwitches(){

           add(new PermissionSwitch("LoadTableCommand (Übersicht Patienten neu laden)"));
           add(new PermissionSwitch("OpenEditorCommand (Patient bearbeiten)"));
           add(new PermissionSwitch("DeleteBoCommand (Patient löschen)"));
           add(new PermissionSwitch("OpenEditorCommand_New (neue Patient anlegen)"));
           add(new PermissionSwitch("OpenEditorCommand_Copy (Patient kopieren)"));
           add(new PermissionSwitch("CreateNodeCommand (neuen Eintrag)"));
           add(new PermissionSwitch("CreateNodeCommand (neuer Eintrag)"));
           add(new PermissionSwitch("CopyNodeCommand (Eintrag kopieren)"));
           add(new PermissionSwitch("DeleteNodeCommand (Eintrag löschen)"));
           add(new PermissionSwitch("SaveBoCommand (Patient speichern)"));
           add(new PermissionSwitch("CreateDokumentCommand (neues Dokument)"));
           add(new PermissionSwitch("DeleteNodeCommand (Dokument löschen)"));
    }


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}
