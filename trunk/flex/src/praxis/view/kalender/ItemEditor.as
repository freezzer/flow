/* 

 generated code by flow "flex on wings"
 this code is property of Andreas Marochow

 */

package praxis.view.kalender {
import de.ama.framework.data.*;
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;

import flash.events.MouseEvent;

import mx.core.DragSource;
import mx.managers.DragManager;

import praxis.bom.CalendarEntry;

public class ItemEditor  extends EditPanel {
	
    public function ItemEditor() {
    	super();
        height = 120;
        setStyle("left", 2);
        setStyle("right", 2);
        setStyle("borderStyle", "solid");
        setStyle("backgroundColor", 0xffffff);
        clipContent = true;
        verticalScrollPolicy = "off";
        horizontalScrollPolicy = "off";
    }

    override public function addFields():void {
        var field:EditField;
        insertLabelField("","time",0,1,2,100);

        insertLabelField("","date",107,1,2,112);
        getEditField("date").setStyle("textAlign","right");

        insertTextField("","label",-7,27,2,225);
        insertTextAreaField("","text",-7,54,2,225,72);
        
    }

}
}
