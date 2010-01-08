/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package de.ama.services.permission.view {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.EditPanel;
public class PermissionContextPanel  extends EditPanel {
    public function PermissionContextPanel() {
        x=15;  y=15; 
        setStyle("borderStyle","none");
        label="Berechtigungs Kontext";
    }
   
     override public function addPanels():void {
        var panel:EditPanel;
     } 
   
     override public function addFields():void {
        _fieldLabelWidth = 70;
        insertTextField("Kontext","context",10,20,70,240);
        insertTextField("UserName","userName",10,50,70,240);
        insertBoolField("Permitted","permitted",10,80,70,240);
        var lf:ListField = insertListField("Einzelberechtigungen","switches","PermissionSwitchesLister",262,20,470,500);
        lf.addCommand(new SelectAllCommand()); 
        lf.addCommand(new SelectNoneCommand());
         

        width = 746; height = 530; 
     }

}}

