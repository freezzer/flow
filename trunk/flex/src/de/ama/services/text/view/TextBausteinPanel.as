/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package de.ama.services.text.view {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;
import de.ama.framework.data.*;
import de.ama.services.Factory;
public class TextBausteinPanel  extends EditPanel { 
    public function TextBausteinPanel() {
        x=10;  y=10;
        setStyle("borderStyle","none");
        width=650;
        height=600;
    }
   
     override public function addPanels():void {
        var panel:EditPanel;
     } 
   
     override public function addFields():void {
        var field:EditField;
 
        field = insertTextField("Tasten Sequenz","key" ,10,20,100 ,300);
 
        field = insertTextAreaField("Text","text" ,10,50,100 ,300);
     } 

}}
