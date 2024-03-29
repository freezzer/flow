package de.ama.framework.gui.fields {
import de.ama.framework.command.Invoker;
import de.ama.framework.command.OpenEditorCommand;
import de.ama.framework.command.SelectBoCommand;
import de.ama.framework.data.BoReference;
import de.ama.framework.data.BusinessObject;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.framework.util.Callback;
import de.ama.framework.util.Util;
import de.ama.services.Factory;

import mx.controls.TextInput;

public class ProxyField extends EditField implements Invoker{
    private var _data:BusinessObject;
    private var _guiRepPath:String;
    private var _type:String;
    private var _searchButton:CommandButton;

    private var _lister:String=null;
    private var _listPanel:ListPanel;

    private var _editor:String=null;
    private var _editButton:CommandButton;

    public function ProxyField(caption:String="ProxyField", path:String = null) {
        super(caption, path);
    }


    public function setData(data:BusinessObject):void {
        setValue(data);
    }

    public function getData():BusinessObject {
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
           var c:Class = Factory.createBeanClass(type);
           sm.type = Util.getClassName(c);
           return sm;
        }
    }

    override protected function createAditionals():void {
    	createSearchButton();
        createEditButton();
        layout();
    }

    private function createSearchButton():void {
    	if(_searchButton) return;
        _searchButton = new CommandButton(new SelectBoCommand(),this,CommandButton.SMALL);
        addChild(_searchButton);
    }

    private function createEditButton():void {
    	if(hasEditButton()) return;
        if(!Util.isEmpty(_editor)){
            _editButton = new CommandButton(new OpenEditorCommand("bearbeiten","edit",_editor),this,CommandButton.SMALL);
            addChild(_editButton);
        }
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

    public function get editor():String {
        return _editor;
    }

    public function set editor(value:String):void {
        _editor = value;
        createEditButton();
        layout();
    }


    public function set lister(value:String):void {
        _lister = value;
    }

    public function get lister():String {
        return _lister;
    }

    public function setListPanel(value:ListPanel):void {
        _listPanel = value;
    }

    public function getListPanel():ListPanel {
        if(_listPanel==null){
           _listPanel = Factory.createLister(_lister); 
        }
        return _listPanel;
    }

    override public function layout():void {
        super.height=25;
        _input.x = labelWidth +10;
        _input.width = width-labelWidth-15-25-(hasEditButton()?25:0);
        _label.width = labelWidth;
        _searchButton.x = super.width-25;
        _searchButton.y = 2;
        _searchButton.width = 20;
        _searchButton.height = 20;
        if(hasEditButton()){
            _searchButton.x = super.width-50;
            _editButton.x = super.width-25;
            _editButton.y = 2;
            _editButton.width = 20;
            _editButton.height = 20;
        }
    }

    private function hasEditButton():Boolean {
        return _editButton != null;
    }

    override public function writeToData():void {
        if(hasData()){
           var d:BusinessObject = parentEditPanel.getData();
           d.setValue(localpath, new BoReference(getData()));
        }
    }

    override public function getValue():Object {
        return _data;
    }

    override public function setValue(val:Object):void {
       if(val is BoReference){
           var boRef:BoReference = BoReference(val);
           boRef.getBusinessObject(new Callback(this,setData))
       }

       if(val is BusinessObject){
           _data = BusinessObject(val);
       }

        
       if(val == null ){
           _data = null;
       }

       if(val is String && String(val).length==0){
           _data = null;
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

    override public function getTextAtCaret():Object {
    	return null;
    }


    override public function getSourceCode(xml:Boolean):String {
        if(xml){
            return "<lookup x=\""+x+"\" y=\""+y+"\" w=\""+width+"\" labelwidth=\""+labelWidth+"\" label=\""+label+"\" path=\""+localpath+"\" type=\""+type+"\" guirep=\""+guiRepPath+"\"/>";
        } else {
           return "insertProxyField(\""+label+"\",\""+guiRepPath+"\",\""+localpath+"\","+x+","+y+","+labelWidth+","+width+");";
        }
    }

}
}