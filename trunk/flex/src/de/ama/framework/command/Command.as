package de.ama.framework.command {
import de.ama.framework.data.BusinessObject;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.gui.icons.IconStore;
import de.ama.framework.util.Util;
import de.ama.services.Environment;

import de.ama.services.permission.PermissionService;

import flash.ui.ContextMenuItem;

public class Command {

    public var label:String;
    public var _iconName:String;
    private var _invoker:Invoker = null;

    private var _contextMenuItem:ContextMenuItem=null;
    private var _permissionId:String;

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

    public function Command(label:String="", iconName:String="") {
        this.label = label;
        this._iconName = iconName;
    }

    public function get icon():Class {
        var iconClass:Class;
        return IconStore.getIcon(_iconName);
    }


    public function get contextMenuItem():ContextMenuItem {
        return _contextMenuItem;
    }

    public function set contextMenuItem(value:ContextMenuItem):void {
        _contextMenuItem = value;
    }

    public function getData(required:Boolean):BusinessObject{
        if(required && _invoker == null){
            throw new Error("CommandContext Illegal state: invoker is required in getData()");
        }

        if(required && _invoker.getData() == null){
            throw new Error("invoker has null Data");
        }

        return _invoker.getData();
    }


    public function get selectionModel():SelectionModel {
        return _invoker.getSelectionModel();
    }

    public function get invoker():Invoker {
        return _invoker;
    }

    public function start(invoker:Invoker):void{
        _invoker=invoker;
        try {
            execute();
        } catch(e:Error) {
            Util.showError("Aktion fehlgeschlagen ("+e.message+")", e.getStackTrace())
        } finally {
            _contextMenuItem = null;
            _invoker = null;
        }
    }

    protected function execute():void{

    }

    //////////////////////////////// permission /////////////////////////////////

    public function set permissionId(value:String):void {
        _permissionId = value;
    }

    public function isPermitted():Boolean {
    	if(Util.isEmpty(_permissionId) || Util.isEqual("true",getProperty("permitted"))) {
    		return true;
    	}
    	return PermissionService.instance.isPermitted(this);
    }

    public function get enabled():Boolean {
        return isPermitted();
    }

    public function get permissionContext():String {
        return _permissionId.split(":")[0];
    }

    public function get permissionKey():String {
        return _permissionId.split(":")[1];
    }

    public function isDefaultCommand():Boolean {
        return Util.isEqual("true",getProperty("default"));
    }

}
}