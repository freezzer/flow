package de.ama.framework.gui.frames {
import mx.controls.dataGridClasses.DataGridColumn;

public class ListPanelColumn extends DataGridColumn{

    private var _type:String;


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

    function get label():String {
        return super.headerText;
    }}
}