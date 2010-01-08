/* 

 generated code by flow "flex on wings"
 this code is property of Andreas Marochow

 */

package de.ama.framework.gui.frames {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;
import de.ama.framework.data.*;
import de.ama.services.Factory;

public class PanelEditor  extends EditPanel {
    private var _listPanel:ListPanel;


    public function SearchPanel(listPanel:ListPanel) {
        _listPanel = listPanel;
    }

    override public function addPanels():void {
        var panel:EditPanel;
    }

    override public function addFields():void {
        var field:EditField;

        var collumns:Array = _listPanel.getColumns();
        for each (var col:ListPanelColumn in collumns) {
            if(!col.searchable) continue;
            var type:String = col.type;
            var name:String = col.label;
            switch (type) {
                case "Date":  {
                    insertDateField(label);
                    break;
                }
                case "Number":{
                    insertTextField(label);
                    break;
                }
                case "Boolean":{
                    insertBoolField(label);
                    break;
                }
                case "String": {
                    insertTextField(label);
                    break;
                }
            }

        }

        field = insertTextField("Ort", "ort", 10, 20, 100, 300);

    }

}
}
