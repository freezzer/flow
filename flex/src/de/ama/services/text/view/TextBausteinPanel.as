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
        height=230;
    }
   
     override public function addFields():void {
        insertTextField("Tasten Sequenz","key",10,20,120,300);
        insertTextAreaField("Text","text",10,50,120,530,135);
     } 

}}
