

package de.ama.framework.gui.frames {
import de.ama.framework.command.CallbackCommand;
import de.ama.framework.command.Invoker;
import de.ama.framework.data.BusinessObject;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.gui.fields.*;
import de.ama.framework.util.Callback;
import de.ama.framework.util.Condition;
import de.ama.framework.util.Util;

import mx.containers.Panel;
import mx.effects.Move;

public class SearchPanel  extends Panel{

    private var _listPanel:ListPanel;
    private var _editPanel:EditPanel;
    private var _buttonbar:CommandButtonBar;

    private var slideIn:Move = new Move(this); 
    private var slideOut:Move = new Move(this); 

    public function SearchPanel(listPanel:ListPanel) {

        width = 400;
        setStyle("backgrondAlpha",0.8);
        setStyle("verticalGap",0);
        setStyle("backgroundColor",0x505090);
        setStyle("horizontalGap",0);
       	
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
        _editPanel.percentWidth = 100;
        x=listPanel.width;
        y=53;
        slideIn.xFrom=x;   slideIn.xTo=listPanel.width-400;  slideIn.duration =500;
        slideOut.xFrom=listPanel.width-400;  slideOut.xTo=listPanel.width;  slideOut.duration =500;
			
    }

    private function refreshListPanel(invoker:Invoker):void {
        _listPanel.reload();
    }

    private function close(invoker:Invoker):void {
        show(false);
    }
    
    public function show(show:Boolean):void {
    	if(show){
           slideIn.play();
    	} else {
           slideOut.play();
    	}
    }
    
    public function addFields():void {
        var field:EditField;

        var collumns:Array = _listPanel.getColumns();
        for each (var col:ListPanelColumn in collumns) {
            if(!col.searchable) continue;
            var type:String = col.type.toLowerCase();
            var name:String = col.label;
            var path:String = col.dataField;
            switch (type) {
                case "date":  {
                    var oldgap:int = _editPanel.gap;
                    _editPanel.gap = 2;
                    _editPanel.insertDateField(name, path);
                    _editPanel.insertDateField(" bis", path);
                    _editPanel.gap = oldgap;
                    break;
                }
                case "number":{
                    _editPanel.insertTextField(name, path);
                    break;
                }
                case "boolean":{
                    _editPanel.insertBoolField(name, path);
                    break;
                }
                case "string": {
                    _editPanel.insertTextField(name, path);
                    break;
                }
            }
        }
    }

    public function getCondition():Condition {
        var cond:Condition = new Condition();
        var path:String = null;
        var ef:EditField = null;
        var collumns:Array = _listPanel.getColumns();
        for each (var col:ListPanelColumn in collumns) {
            if(!col.searchable) continue;
            path = col.dataField;
            cond.and(buildSubCondition(path));
        }
        return cond;
    }
    
    private function buildSubCondition(path:String):Condition{
        var cond:Condition = null;
        var ef:EditField = null;

        var fields:Array = new Array();
        _editPanel.getEditField(path,fields);

        if(fields.length == 1){
            ef = EditField(fields[0]);
            var str:String = ef.getInputText();
            var parts:Array = str.split(",");
            if(parts.length > 1){
                for each(var part:String in parts){
                	if(!Util.isEmpty(part)){
                 	   if(cond==null){ cond = new Condition(); }
                       cond.or( new Condition(path, part.indexOf("*")>=0? Condition.LIKE : Condition.EQ , part) );
                    }
                }
            } else {
                if(!ef.isEmpty()){
                    cond = new Condition(path, ef.getInputText().indexOf("*")>=0? Condition.LIKE : Condition.EQ , ef.getValue());
                }
            }
        }

        // Intervalle
        if(fields.length == 2){
          	cond = new Condition();
            ef = EditField(fields[0]);
            if(!ef.isEmpty()){
                cond.and( new Condition(path, Condition.GE, ef.getValue()) );
            }
            ef = EditField(fields[1]);
            if(!ef.isEmpty()){
                cond.and( new Condition(path, Condition.LT , ef.getValue()) );
            }
        }

        return cond;
    }


    public function setData(bo:BusinessObject):void     {     }
    public function getData():BusinessObject            {        return null;    }
    public function getSelectionModel():SelectionModel  {        return null;    }
}
}
