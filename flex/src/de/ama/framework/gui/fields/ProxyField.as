package de.ama.framework.gui.fields {
import de.ama.framework.command.Invoker;
import de.ama.framework.command.OpenEditorCommand;
import de.ama.framework.command.SelectBoCommand;
import de.ama.framework.data.Data;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.util.Util;

import mx.controls.TextInput;

public class ProxyField extends EditField implements Invoker{
    private var _data:Data;
    private var _guiRepPath:String;
    private var _type:String;
    private var _searchButton:CommandButton;
    private var _editButton:CommandButton;

    public function ProxyField(caption:String="ProxyField", path:String = null) {
        super(caption, path);
    }


    public function setData(data:Data):void {
        setValue(data);
    }

    public function getData():Data {
        return _data;
    }

    private function hasData():Boolean {
        return (_data!=null);
    }

    public function getSelectionModel():SelectionModel {
        if(hasData()){
           return new SelectionModel(getData());
        } else {
           var sm:SelectionModel= new SelectionModel();
           sm.type = type;
           return sm;
        }
    }

    override protected function createAditionals():void {
        _searchButton = new CommandButton();
        _searchButton.command = new SelectBoCommand();
        _searchButton.invoker = this;
        addChild(_searchButton);

        _editButton = new CommandButton();
        _editButton.command = new OpenEditorCommand();
        _editButton.invoker = this;
        addChild(_editButton);
        layout();
    }

    public function get guiRepPath():String {
        return _guiRepPath;
    }

    public function set guiRepPath(value:String):void {
        _guiRepPath = value;
    }


    public function get type():String {
        return _type;
    }

    public function set type(value:String):void {
        _type = value;
    }

    override public function layout():void {
        super.height=25;
        _input.x = labelWidth +10;
        _input.width = width-50-labelWidth-15;
        _label.width = labelWidth;
        _searchButton.x = super.width-50;
        _searchButton.y = 2;
        _searchButton.width = 20;
        _searchButton.height = 20;
        _editButton.x = super.width-25;
        _editButton.y = 2;
        _editButton.width = 20;
        _editButton.height = 20;
    }

    override public function getValue():Object {
        return _data;
    }

    override public function setValue(val:Object):void {
       if(val is Data){
           _data = Data(val);
       }

       TextInput(_input).text = guiRep;
    }

    public function get guiRep():String {
        if (hasData()) {
            if (Util.isEmpty(_guiRepPath)) {
                return _data.getGuiRepresentation();
            }

            if (_guiRepPath.indexOf("{") >= 0 && _guiRepPath.indexOf("}") > 0) {
                var pre:String = _guiRepPath.split("{")[0];
                var post:String = _guiRepPath.split("}")[1];
                var lpath:String = _guiRepPath.substring(_guiRepPath.indexOf("{") + 1, _guiRepPath.indexOf("}"));
                var prop:String = Util.asString(_data.getValue(lpath));
                return pre + prop + post;
            } else {
            	return Util.asString(_data.getValue(_guiRepPath));
            } 
            
        }
        return "";
    }


    override public function getSourceCode(xml:Boolean):String {
        if(xml){
            return "<lookup x=\""+x+"\" y=\""+y+"\" w=\""+width+"\" labelwidth=\""+labelWidth+"\" label=\""+label+"\" path=\""+localpath+"\" type=\""+type+"\" guirep=\""+guiRepPath+"\"/>";
        } else {
           return "insertProxyField(\""+label+"\",\""+guiRepPath+"\",\""+localpath+"\","+x+","+y+","+labelWidth+","+width+");";
        }
    }

    public function reload():void {

    }}
}