package de.ama.framework.gui.frames {
import mx.controls.dataGridClasses.DataGridColumn;

public class ListPanelColumn extends DataGridColumn{

    private var _type:String;
    private var _searchable:Boolean;


    public function ListPanelColumn(label:String,type:String) {
        super(label);
        _type=type;
    }


    public function get type():String {
        return _type;
    }

    public function set type(value:String):void {
        _type = value;
    }

    public function get label():String {
        return super.headerText;
    }


    public function get searchable():Boolean {
        return _searchable;
    }

    public function set searchable(value:Boolean):void {
        _searchable = value;
    }
}
}