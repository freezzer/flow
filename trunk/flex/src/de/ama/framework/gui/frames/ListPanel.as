package de.ama.framework.gui.frames {
import de.ama.framework.action.LoadTableAction;
import de.ama.framework.command.Command;
import de.ama.framework.command.CreateNodeCommand;
import de.ama.framework.command.Invoker;
import de.ama.framework.data.BusinessObject;
import de.ama.framework.data.DataProvider;
import de.ama.framework.data.QueryDataProvider;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.util.Callback;
import de.ama.framework.util.Util;
import de.ama.services.Factory;

import flash.display.DisplayObject;
import flash.events.ContextMenuEvent;
import flash.events.KeyboardEvent;
import flash.ui.ContextMenu;
import flash.ui.ContextMenuItem;
import flash.ui.Keyboard;
import flash.utils.describeType;

import mx.collections.ArrayCollection;
import mx.containers.Canvas;
import mx.containers.VBox;
import mx.controls.DataGrid;
import mx.core.IDataRenderer;
import mx.events.ListEvent;

public class ListPanel extends Canvas implements Panel,Invoker{

    protected var rows:ArrayCollection = new ArrayCollection();

    private var _searchPanel:SearchPanel = null;
    private var _commands:ArrayCollection = new ArrayCollection();
    private var _listMenu:ContextMenu = new ContextMenu();
    private var _generic:Boolean = false;
    private var _toolbarSize:int = 0;
    private var _toolbar:CommandButtonBar;
    private var _grid:DataGrid;
    private var _center:VBox;
    private var _singleClickCallback:Callback;
    private var _doubleClickCallback:Callback;

    private var _emptyData:BusinessObject = null;

    private var _dataProviderName:String=null;
    private var _dataProvider:DataProvider=null;

    public function setDataProvider(dp:DataProvider):void {
        _dataProvider = dp;
    }

    public function set dataProviderName(value:String):void {
        _dataProviderName = value;
    }

    public function ListPanel(generic:Boolean = false):void {
        setStyle("left",0);
        setStyle("right",0);
        setStyle("top",0);
        setStyle("bottomm",0);
        _generic = generic;

        _center = new VBox();
        _center.setStyle("left",0);
        _center.setStyle("right",0);
        _center.setStyle("top",0);
        _center.setStyle("bottom",0);
        _center.setStyle("verticalGap",0);
        addChild(_center)

        createGrid();
        createToolbar();

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
            _grid.doubleClickEnabled = true;
            _grid.addEventListener(ListEvent.ITEM_DOUBLE_CLICK,onItemDoubleClick); 
            _grid.addEventListener(ListEvent.ITEM_CLICK,onItemSingleClick);
            _grid.addEventListener(KeyboardEvent.KEY_UP,onKeyPressed);
            _center.addChild(_grid);
        }
    }

    private function onKeyPressed(event:KeyboardEvent):void {
//        var lowerkey:String = String.fromCharCode(event.charCode).toLowerCase();
        if (event.keyCode == Keyboard.ENTER) {
            startDefaultCommands();
        }
        if (event.keyCode == Keyboard.F3) {
            showSearchPanel();
        }
    }


    private function get searchPanelVisible():Boolean {
        return _searchPanel !=null && _searchPanel.visible;
    }

    private function showSearchPanel():void {
        if(_searchPanel==null){
            _searchPanel = new SearchPanel(this);
            _searchPanel.width = grid.width/2;
            _searchPanel.height = grid.height;
            addChild(_searchPanel);
        }
        _searchPanel.visible = true;
    }

    private function hideSearchPanel():void {
        if(_searchPanel==null) return;
        _searchPanel.visible = false;
    }

    public function set singleClickCallback(value:Callback):void {
        _singleClickCallback = value;
    }

    public function set doubleClickCallback(value:Callback):void {
        _doubleClickCallback = value;
    }

    public function onItemDoubleClick(le:ListEvent):void {
    	if(_doubleClickCallback!=null){
            _doubleClickCallback.execute(this)
        } else {
            startDefaultCommands();
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

    public function startDefaultCommands():void{
        for each(var command:Command in _commands) {
            if(command.isDefaultCommand()){
               command.start(this);
            }
        }
    }
    /////////////////////////////////// Toolbar ///////////////////////////////////////////

    public function createToolbar():void {
        if (_toolbarSize > 0) {
            _toolbar = new CommandButtonBar();

            _toolbar.size = _toolbarSize;
            _toolbar.label = label;
            _toolbar.invoker = this;
            _toolbar.commands = _commands
            _center.addChildAt(DisplayObject(_toolbar), 0);
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
        var data:BusinessObject = null;

        if (event.mouseTarget is IDataRenderer) {
            data = BusinessObject(IDataRenderer(event.mouseTarget).data);
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

    public function addCollumn(type:String,label:String, path:String, editable:Boolean,  searchable:Boolean, w:int = 100):void {
        var col:ListPanelColumn = new ListPanelColumn(label , type);
        col.dataField = path;
        col.editable = editable;
        col.searchable = searchable;
        col.width = w;
        var array:Array = _grid.columns;
        array.push(col);
        _grid.columns = array;
        if (editable) {
            _grid.editable = true;
        }
    }

    public function createDefaultColumns(data:BusinessObject):void {
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
                    addCollumn(type,Util.firstCharToUpper(name), name, false,true);
                    break;
                }
            }
        }
    }

    public function getColumns():Array{
        return _grid.columns;
    }


    public function get grid():DataGrid {
        return _grid;
    }

    ///////////////////////////////////////////////// Data ///////////////////////////////////////////

    public function getSelectionModel():SelectionModel {
        var selectionModel:SelectionModel = new SelectionModel();
        var array:Array = _grid.selectedItems;
        for each (var data:BusinessObject in array) {
            selectionModel.addSelection(data);
        }

        if (selectionModel.getSelections().length<1) {
            selectionModel.type = Util.getClassName(getData());
        }

        if(_searchPanel!=null){
            selectionModel.getQuery().condition = _searchPanel.getCondition();
        }
        return selectionModel;
    }

    public function hasDataProvider():Boolean {
        return _dataProvider != null;
    }

    public function getDataProvider():DataProvider{
        if(_dataProvider==null && !Util.isEmpty(_dataProviderName)){
           _dataProvider = Factory.createProvider(_dataProviderName);
           _dataProvider.invoker=this;
        }
        if(_dataProvider==null){
           _dataProvider = new QueryDataProvider();
           _dataProvider.invoker=this;
        }
        return _dataProvider;
    }

    public function reload():void {
        getDataProvider().getTable(new Callback(this,setDataTable));
    }

    private function loadTableHandler(a:LoadTableAction):void {
        setDataTable(a.data as Array);
    }

    public function getDataTable():Array {
        return rows.source;
    }

    public function setDataTable(array:Array):void {
        if (_generic && array != null && array.length > 0) {
            createDefaultColumns(array[0]);
        }
        rows = new ArrayCollection(array);
        _grid.dataProvider = rows;
    }

    public function addNewRow():void {
        var data:BusinessObject = createData();
        rows.addItem(data);
        _grid.selectedItem = data;
    }

    public function copySelectedRow():void {
        if (_grid.selectedIndex < 0 && _grid.selectedItem == null) return;
        var data:BusinessObject = BusinessObject(_grid.selectedItem).clone();
        rows.addItem(data);
        _grid.selectedItem = data;
    }

    public function removeSelectedRow():void {
        if (_grid.selectedIndex < 0 && _grid.selectedItem == null) return;
        var newIndex:int = Math.max(_grid.selectedIndex - 1, 0);
        var data:BusinessObject = BusinessObject(_grid.selectedItem);
        rows.removeItemAt(rows.getItemIndex(data));
        _grid.selectedIndex = newIndex;
    }

    public function getRowCount():int {
        if (rows == null) {
            return 0;
        }
        return rows.length;
    }

    public function setData(data:BusinessObject):void {
    }

    public function getData():BusinessObject {
        if(_emptyData==null){
            _emptyData = createData();
        }
        return _emptyData;
    }


    /////////////////////////////////// Overwrites ///////////////////////////////////////////

    public function createData():BusinessObject {
        return null;
    }

    public function addCollumns():void {
    }

    public function addCommands():void {
        addCommand(new CreateNodeCommand());
    }

}

}

