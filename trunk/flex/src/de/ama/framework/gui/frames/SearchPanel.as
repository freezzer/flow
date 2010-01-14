/* 

 generated code by flow "flex on wings"
 this code is property of Andreas Marochow

 */

package de.ama.framework.gui.frames {
import de.ama.framework.gui.fields.*;
import de.ama.framework.util.Condition;

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
                    insertDateField(label, col.dataField);
                    break;
                }
                case "number":{
                    insertTextField(label, col.dataField);
                    break;
                }
                case "boolean":{
                    insertBoolField(label, col.dataField);
                    break;
                }
                case "string": {
                    insertTextField(label, col.dataField);
                    break;
                }
            }
        }
    }

    public function getCondition():Condition {
        var cond:Condition = null;
        var path:String = null;
        var collumns:Array = _listPanel.getColumns();
        for each (var col:ListPanelColumn in collumns) {
            if(!col.searchable) continue;
            path = col.dataField;
            if(cond==null){
                cond = new Condition(path, Condition.EQ , getEditField(path).getValue());
            } else {
                cond.and(new Condition(path, Condition.EQ , getEditField(path).getValue()));
            }
        }
        return cond;
    }

}
}
