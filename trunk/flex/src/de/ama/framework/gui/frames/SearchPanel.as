/* 

 generated code by flow "flex on wings"
 this code is property of Andreas Marochow

 */

package de.ama.framework.gui.frames {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;
import de.ama.framework.data.*;
import de.ama.services.Factory;

public class SearchPanel  extends EditPanel {
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
            var type:String = col.type;
            var name:String = col.label;
            switch (type) {
                case "Date":
                case "Boolean":
                case "Number":
                case "String": {
                    break;
                }
            }

        }

        field = insertTextField("Ort", "ort", 10, 20, 100, 300);

    }

}
}
