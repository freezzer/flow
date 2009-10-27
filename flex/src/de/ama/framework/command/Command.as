package de.ama.framework.command {
import de.ama.framework.data.Data;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.gui.icons.IconStore;

import de.ama.framework.util.Util;

import flash.ui.ContextMenuItem;

public class Command {

    public var label:String;
    public var icon:String;
    private var _invoker:Invoker = null;
    private var _selectionModel:SelectionModel = null;

    private var _contextMenuItem:ContextMenuItem=null;

    ////////////////////////////// Properties ////////////////////////////////////////

    private var _properties:Array = null;

    public function setProperty(key:String, value:String):void {
        if(_properties==null){
            _properties = new Array();
        }
        _properties[key] = value;
    }

    public function getProperty(key:String, def:String=""):String {
        if(_properties==null){
            return def;
        }
        return Util.saveToString(properties[key],def);
    }

    public function get properties():Array {
        return _properties;
    }

    public function set properties(value:Array):void {
        _properties = value;
    }

    ///////////////////////////////////////////////////////////////////////////////////

    public function Command(label:String="", icon:String="") {
        this.label = label;
        this.icon = icon;
    }

    public function getIcon():Class {
        var iconClass:Class;
        return IconStore.getIcon(icon);
    }


    public function get contextMenuItem():ContextMenuItem {
        return _contextMenuItem;
    }

    public function set contextMenuItem(value:ContextMenuItem):void {
        _contextMenuItem = value;
    }

    public function getData(required:Boolean):Data{
        if(required && _invoker == null){
            throw new Error("CommandContext Illegal state: invoker is required in getData()");
        }

        if(required && _invoker.getData() == null){
            throw new Error("invoker has null Data");
        }

        return _invoker.getData();
    }


    public function get selectionModel():SelectionModel {
        return _selectionModel;
    }

    public function get invoker():Invoker {
        return _invoker;
    }

    public function start(invoker:Invoker):void{
        _invoker=invoker;
        _selectionModel=_invoker.getSelectionModel();
        execute();
        _contextMenuItem = null;
    }

    protected function execute():void{

    }
}
}