/* 

 generated code by flow "flex on wings"
 this code is property of Andreas Marochow

 */

package de.ama.framework.gui.frames {
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;
import de.ama.framework.data.*;
import de.ama.framework.util.Query;
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
            if(!col.searchable) continue;
            var type:String = col.type.toLowerCase();
            var name:String = col.label;
            switch (type) {
                case "dDate":  {
                    insertDateField(label);
                    break;
                }
                case "number":{
                    insertTextField(label);
                    break;
                }
                case "boolean":{
                    insertBoolField(label);
                    break;
                }
                case "string": {
                    insertTextField(label);
                    break;
                }
            }
        }
    }

    public function getQuery():Query{
        var q:Query = new Query(_listPanel.getType());
        var fields:Array = getAllGUIComponents(EditField);
        for each (var ef:EditField in fields) {

        }
    }

}
}
