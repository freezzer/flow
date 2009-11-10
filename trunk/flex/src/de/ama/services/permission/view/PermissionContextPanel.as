/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package de.ama.services.permission.view {
import de.ama.framework.gui.fields.*;
import de.ama.services.Factory;
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
        var field:EditField;
        field = insertBoolField("Permitted","permitted");
        field = insertTextField("UserName","userName");
        field = insertTextField("Kontext","context");

        field = insertListField("Einzelberechtigungen","switches","PermissionSwitchesLister",470,20,500);
        
     } 

}}
