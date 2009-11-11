/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package de.ama.services.permission.view {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.EditPanel;
public class PermissionContextPanel  extends EditPanel {
    public function PermissionContextPanel() {
        x=0;  y=0; 
        setStyle("borderStyle","none");
        label="Berechtigungs Kontext";
    }
   
     override public function addPanels():void {
        var panel:EditPanel;
     } 
   
     override public function addFields():void {
        labelWidth = 70;
        insertTextField("Kontext","context");
        insertTextField("UserName","userName");
        insertBoolField("Permitted","permitted");
        var lf:ListField = insertListField("Einzelberechtigungen","switches","PermissionSwitchesLister",470,20,500);
        lf.addCommand(new SelectAllCommand()); 
        lf.addCommand(new SelectNoneCommand());
     }

}}
