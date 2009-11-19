package de.ama.framework.gui.fields {
import de.ama.framework.command.Invoker;
import de.ama.framework.command.SelectBoCommand;
import de.ama.framework.data.Data;
import de.ama.framework.data.Data;
import de.ama.framework.data.Data;
import de.ama.framework.data.Selection;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.util.Util;

import mx.controls.TextInput;

public class ProxyField extends EditField implements Invoker{
    private var _selection:Selection;
    private var _guiRepPath:String;

    public function ProxyField(caption:String="ProxyField", path:String = null) {
        super(caption, path);
    }


    public function setData(data:Data):void {
        setValue(data);
    }

    public function getData():Data {
        if(hasData()){
            return _selection.data;
        }
        return null;
    }

    private function hasData():Boolean {
        return (_selection!=null && _selection.hasData());
    }

    public function getSelectionModel():SelectionModel {
        return new SelectionModel(getData());
    }

    override public function createInput():void {
        super.createInput();
        var searchButton:CommandButton = new CommandButton();
        searchButton.command = new SelectBoCommand();
        searchButton.invoker = this;
        searchButton.x = super.width-25;
        searchButton.width = 25;
        addChild(searchButton);

    }

    public function get guiRepPath():String {
        return _guiRepPath;
    }

    public function set guiRepPath(value:String):void {
        _guiRepPath = value;
    }

    override public function getValue():Object {
        return _selection;
    }

    override public function setValue(val:Object):void {
       if(val is Selection){
           _selection = Selection(val);
       }
       if(val is Data){
           _selection = new Selection(Data(val));
       }

       TextInput(_input).text = guiRep;
    }

    public function get guiRep():String {
        if (_selection != null && _selection.hasData()) {
            if (Util.isEmpty(_guiRepPath)) {
                return _selection.data.getGuiRepresentation();
            }

            if (_guiRepPath.indexOf("{") >= 0 && _guiRepPath.indexOf("}") > 0) {
                var pre:String = _guiRepPath.split("{")[0];
                var post:String = _guiRepPath.split("}")[1];
                var lpath:String = _guiRepPath.substring(_guiRepPath.indexOf("{") + 1, _guiRepPath.indexOf("}"));
                var prop:String = Util.asString(_selection.data.getValue(lpath));
                return pre + prop + post;
            }
        }
        return "";
    }


    override public function getSourceCode(xml:Boolean):String {
        if(xml){
            return "<input x=\""+x+"\" y=\""+y+"\" w=\""+width+"\" labelwidth=\""+labelWidth+"\" h=\""+height+"\" label=\""+label+"\" path=\""+localpath+"\" type=\"proxy\" guirep=\""+guiRepPath+"\"/>";
        } else {
           return "insertProxyField(\""+label+"\",\""+localpath+"\",\""+guiRepPath+"\","+x+","+y+","+labelWidth+","+width+");";
        }
    }

    function reload():void {

    }}
}