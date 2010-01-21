/* 

 generated code by flow "flex on wings"
 this code is property of Andreas Marochow

 */

package praxis.view.kalender {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;
import de.ama.framework.data.*;
import de.ama.services.Factory;

import praxis.bom.CalendarEntry;

public class ItemEditPanel  extends EditPanel {
    public function ItemEditPanel() {
    	super();
        height = 120;
        x = 20;
        setStyle("left", 2);
        setStyle("right", 2);
        setStyle("borderStyle", "solid");
        setStyle("backgroundColor", 0xffffff);
        clipContent = true;
        verticalScrollPolicy = "off";
        horizontalScrollPolicy = "off";
    }


    override public function addPanels():void {
        var panel:EditPanel;
    }

    override public function addFields():void {
        var field:EditField;
        insertLabelField("9:30","date",10,1,115,212);
        insertTextField("","label",-7,27,2,225);
        insertTextAreaField("","text",-7,54,2,225,72);
    }

}
}
