package de.ama.framework.gui.frames {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.LoadTableAction;
import de.ama.framework.command.Command;
import de.ama.framework.command.CreateNodeCommand;
import de.ama.framework.command.Invoker;
import de.ama.framework.data.Data;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.util.Callback;
import de.ama.framework.util.Util;

import flash.display.DisplayObject;
import flash.events.ContextMenuEvent;
import flash.ui.ContextMenu;
import flash.ui.ContextMenuItem;
import flash.utils.describeType;

import mx.collections.ArrayCollection;
import mx.containers.VBox;
import mx.controls.DataGrid;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.core.IDataRenderer;
import mx.events.ListEvent;

public class ListPanel extends VBox implements Panel,Invoker{

    protected var rows:ArrayCollection = new ArrayCollection();

    private var _commands:ArrayCollection = new ArrayCollection();
    private var _listMenu:ContextMenu = new ContextMenu();
    private var _generic:Boolean = false;
    private var _toolbarSize:int = 0;
    private var _toolbar:CommandButtonBar;
    private var _grid:DataGrid;
    private var _singleClickCallback:Callback;
    private var _doubleClickCallback:Callback;

    public function ListPanel(generic:Boolean = false):void {
        percentWidth=100;
        percentHeight=100;
        setStyle("paddingTop",0);
        setStyle("paddingRight",0);
        setStyle("paddingLeft",0);
        setStyle("paddingBottom",0);
        setStyle("verticalGap",0);
        _generic = generic;

        createToolbar();
        createGrid();
        addCollumns();
        addCommands();
        _listMenu.addEventListener(ContextMenuEvent.MENU_SELECT, contextMenuTriggered);
        _listMenu.hideBuiltInItems();
    }


    protected function createGrid():void {
        if (_grid == null) {
            _grid = new DataGrid();
            _grid.dataProvider = rows;
            _grid.percentWidth = 100;
            _grid.percentHeight = 100;
            _grid.contextMenu = _listMenu;
            _grid.allowMultipleSelection = true;
            _grid.setStyle("borderStyle", "solid");
            _grid.setStyle("borderColor", 0xa0a0a0);
            _grid.addEventListener(ListEvent.ITEM_DOUBLE_CLICK,onItemDoubleClick); 
            _grid.addEventListener(ListEvent.ITEM_CLICK,onItemSingleClick);
            addChild(_grid);
        }
    }


    public function set singleClickCallback(value:Callback):void {
        _singleClickCallback = value;
    }

    public function set doubleClickCallback(value:Callback):void {
        _grid.doubleClickEnabled = true;
        _doubleClickCallback = value;
    }

    public function onItemDoubleClick(le:ListEvent):void {
    	if(_doubleClickCallback!=null){
            _doubleClickCallback.execute(this)
        }
    }

    public function onItemSingleClick(le:ListEvent):void {
    	if(_singleClickCallback!=null){
            _singleClickCallback.execute(this)
        }
    }

    public function refreshGui():void {
        rows.refresh();
    }


    /////////////////////////////////// Toolbar ///////////////////////////////////////////

    public function createToolbar():void {
        if (_toolbarSize > 0) {
            _toolbar = new CommandButtonBar();

            _toolbar.size = _toolbarSize;
            _toolbar.label = label;
            _toolbar.invoker = this;
            _toolbar.commands = _commands
            addChildAt(DisplayObject(_toolbar), 0);
        }
    }

    public function addCommand(command:Command):void {
        _commands.addItem(command);
    }

    public function set toolbarSize(toolbarSize:int):void {
        _toolbarSize = toolbarSize;
        createToolbar();
    }


    /////////////////////////////////// ContextMenu ///////////////////////////////////////////


    public function contextMenuTriggered(event:ContextMenuEvent):void
    {
        var cmis:Array = new Array();
        var data:Data = null;

        if (event.mouseTarget is IDataRenderer) {
            data = Data(IDataRenderer(event.mouseTarget).data);
        }

        if (data != null) {
            _grid.selectedItem = data;
        }

        for each(var command:Command in _commands) {
            var cmi:ContextMenuItem = new ContextMenuItem(command.label);
            command.contextMenuItem = cmi;
            cmi.addEventListener(ContextMenuEvent.MENU_ITEM_SELECT, menuItemSelectHandler);
            cmis.push(cmi);
        }

        _listMenu.customItems = cmis;
    }

    private function menuItemSelectHandler(event:ContextMenuEvent):void {
        for each(var command:Command in _commands) {
            if (command.contextMenuItem == event.target) {
                command.start(this);
            }
        }
    }


    /////////////////////////////////// Columns ///////////////////////////////////////////

    public function addCollumn(label:String, path:String, editable:Boolean, w:int = 100):void {
        var col:DataGridColumn = new DataGridColumn(label);
        col.dataField = path;
        col.editable = editable;
        col.width = w;
        var array:Array = _grid.columns;
        array.push(col);
        _grid.columns = array;
        if (editable) {
            _grid.editable = true;
        }
    }

    public function createDefaultColumns(data:Data):void {
        var classInfo:XML = describeType(data);
        for each (var v:XML in classInfo..variable) {
            var type:String = v.@type;
            var name:String = v.@name;
            if (Util.isEqual(name, "oid")) continue;
            if (Util.isEqual(name, "version")) continue;
            switch (type) {
                case "Date":
                case "Boolean":
                case "Number":
                case "String": {
                    addCollumn(Util.firstCharToUpper(name), name, false);
                    break;
                }
            }
        }
    }

    ///////////////////////////////////////////////// Data ///////////////////////////////////////////

    public function getSelectionModel():SelectionModel {
        var selectionModel:SelectionModel = new SelectionModel();
        var array:Array = _grid.selectedItems;
        for each (var data:Data in array) {
            selectionModel.addData(data);
        }

        if (selectionModel.getSelections().length<1) {
            selectionModel.addData(createData());
        }
        
        return selectionModel;
    }


    public function reload():void {
        var lta:LoadTableAction = new LoadTableAction();
        lta.data = createData();
        ActionStarter.instance.execute(lta, new Callback(this, loadTableHandler));
    }

    private function loadTableHandler(a:LoadTableAction):void {
        dataTable = a.data as Array;
    }

    public function get dataTable():Array {
        return rows.source;
    }

    public function set dataTable(array:Array):void {
        if (_generic && array != null && array.length > 0) {
            createDefaultColumns(array[0]);
        }
        rows = new ArrayCollection(array);
        _grid.dataProvider = rows;
    }

    public function addNewRow():void {
        var data:Data = createData();
        rows.addItem(data);
        _grid.selectedItem = data;
    }

    public function copySelectedRow():void {
        if (_grid.selectedIndex < 0 && _grid.selectedItem == null) return;
        var data:Data = Data(_grid.selectedItem).clone();
        rows.addItem(data);
        _grid.selectedItem = data;
    }

    public function removeSelectedRow():void {
        if (_grid.selectedIndex < 0 && _grid.selectedItem == null) return;
        var newIndex:int = Math.max(_grid.selectedIndex - 1, 0);
        var data:Data = Data(_grid.selectedItem);
        rows.removeItemAt(rows.getItemIndex(data));
        _grid.selectedIndex = newIndex;
    }

    public function getRowCount():int {
        if (rows == null) {
            return 0;
        }
        return rows.length;
    }

    public function setData(data:Data):void {
    }

    public function getData():Data {
        return null;
    }


    /////////////////////////////////// Overwrites ///////////////////////////////////////////

    public function createData():Data {
        return null;
    }

    public function addCollumns():void {
    }

    public function addCommands():void {
        addCommand(new CreateNodeCommand());
    }

}
}

