/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.view.kalender {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;
import de.ama.framework.data.*;
import de.ama.services.Factory;
public class ItemEditPanel  extends EditPanel { 
    public function ItemEditPanel() {
        x=0;  y=0;
        setStyle("borderStyle","none");
    }
   
     override public function addPanels():void {
        var panel:EditPanel;
     } 
   
     override public function addFields():void {
        var field:EditField;
 
        field = insertTextField("date",".");
        field.editable=false;
     } 

}}
