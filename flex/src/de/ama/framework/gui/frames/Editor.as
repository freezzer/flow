package de.ama.framework.gui.frames {
import de.ama.framework.command.Command;
import de.ama.framework.command.Invoker;
import de.ama.framework.command.SaveBoCommand;
import de.ama.framework.data.BusinessObject;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.util.Callback;
import de.ama.framework.util.Util;

import de.ama.services.permission.PermissionService;

import flash.events.MouseEvent;

import mx.containers.Canvas;
import mx.controls.Button;

public class Editor extends Canvas implements Invoker{

    private var _permissionContext:String;
    protected var _label:String = null;
    protected var _data:BusinessObject = null;
    public var buttonbar:CommandButtonBar;

    //////////////////////////// init ///////////////////////////////////


    override protected function initializationComplete():void {
//        PermissionService.instance.loadPermissionContext(_permissionContext);
    }

    public function onCreationComplete():void {
        addCommands();
        addPanels();
    }

    //////////////////////////// permission ///////////////////////////////////


    private function get permissionContext():String {
        return _permissionContext;
    }

    public function set permissionContext(value:String):void {
        _permissionContext = value;
    }


    //////////////////////////// label ///////////////////////////////////


    override public function set label(txt:String):void {
        _label = txt;
        super.label = txt;
    }

    override public function get label():String {
        if (getData() != null) {
            if (Util.isEmpty(_label)) {
                return getData().getName();
            }

            if (_label.indexOf("{") >= 0 && _label.indexOf("}") > 0) {
                var pre:String = _label.split("{")[0];
                var post:String = _label.split("}")[1];
                var lpath:String = _label.substring(_label.indexOf("{") + 1, _label.indexOf("}"));
                var prop:String = getData().getProperty(lpath);
                return pre + prop + post;
            }
        }
        return _label;
    }

    //////////////////////////// Data ///////////////////////////////////


    public function createData():BusinessObject {
        return null;
    }

    public function getData():BusinessObject {
        return _data;
    }

    public function setData(data:BusinessObject):void {
        if (data == null) {
            data = createData();
        }
        _data = data;
    }

    public function getSelectionModel():SelectionModel {
        return new SelectionModel(getData());
    }

    //////////////////////////// Commands ///////////////////////////////////


    public function addCommand(command:Command):void {
        buttonbar.addCommand(command);
    }


    public function addCommands():void {
        addCommand(new SaveBoCommand());
    }

    //////////////////////////// panels ///////////////////////////////////

    public function addPanels():void {
    }



}
}