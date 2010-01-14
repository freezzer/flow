/* 

 generated code by flow "flex on wings"
 this code is property of Andreas Marochow

 */

package de.ama.framework.gui.frames {
import de.ama.framework.command.CallbackCommand;
import de.ama.framework.command.Invoker;
import de.ama.framework.data.BusinessObject;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.gui.fields.*;
import de.ama.framework.util.Callback;
import de.ama.framework.util.Condition;

import mx.containers.VBox;

public class SearchPanel  extends VBox{

    private var _listPanel:ListPanel;
    private var _editPanel:EditPanel;
    private var _buttonbar:CommandButtonBar;



    public function SearchPanel(listPanel:ListPanel) {
        alpha=0,8;
        _listPanel = listPanel;
        _editPanel = new EditPanel();
        addChild(_editPanel);

        _buttonbar = CommandButtonBar(addChild(new CommandButtonBar()));
        _buttonbar.setStyle("horizontalAlign","right");
        _buttonbar.setStyle("verticalAlign","middle");
        _buttonbar.invoker = _editPanel;
        addChild(_buttonbar);

        _buttonbar.addCommand(new CallbackCommand("Schließen","accept",new Callback(this,close)));
        _buttonbar.addCommand(new CallbackCommand("Aktualisieren","refresh",new Callback(this,refreshListPanel)));

        addFields();
    }

    private function refreshListPanel(invoker:Invoker):void {
        _listPanel.reload();
    }

    private function close(invoker:Invoker):void {
        visible = false;
    }


     public function addFields():void {
        var field:EditField;

        var collumns:Array = _listPanel.getColumns();
        for each (var col:ListPanelColumn in collumns) {
            if(!col.searchable) continue;
            var type:String = col.type.toLowerCase();
            var name:String = col.label;
            switch (type) {
                case "date":  {
                    _editPanel.insertDateField(label, col.dataField);
                    break;
                }
                case "number":{
                    _editPanel.insertTextField(label, col.dataField);
                    break;
                }
                case "boolean":{
                    _editPanel.insertBoolField(label, col.dataField);
                    break;
                }
                case "string": {
                    _editPanel.insertTextField(label, col.dataField);
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
                cond = new Condition(path, Condition.EQ , _editPanel.getEditField(path).getValue());
            } else {
                cond.and(new Condition(path, Condition.EQ , _editPanel.getEditField(path).getValue()));
            }
        }
        return cond;
    }


    public function setData(bo:BusinessObject):void {
    }

    public function getData():BusinessObject {
        return null;
    }

    public function getSelectionModel():SelectionModel {
        return null;
    }
}
}
