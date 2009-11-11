package de.ama.framework.gui.fields {
import de.ama.framework.command.Command;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.framework.util.Util;
import de.ama.services.Factory;

public class ListField extends EditField{
	private var _labelText:String;

    public function ListField(caption:String="ListField", path:String=null, lister:String=null) {
        super(caption,path);
        if(!Util.isEmpty(lister)){
            setListPanel(Factory.createLister(lister));
        }
    }

    public override function createInput():void{
        if(_input==null){
            setListPanel(new ListPanel());
        }
    }


    override public function createLabel(caption:String):void {
		_labelText = caption;
    }
    
    public override function get label():String {
        return _labelText;
    }


    override public function set labelWidth(w:int):void {
    }

    public function setListPanel(lp:ListPanel):void{
        if(_input!=null){
            removeChild(_input);
        }
        lp.useToolbar = ListPanel.SMALL;
        lp.label = _labelText;
        _input = lp;
        _input.x = 5;
        _input.width = super.width - 10;
        _input.height = super.height;
        addChild(_input);
    }

    public override function setValue(val:Object):void {
        ListPanel(_input).dataTable = val as Array;
    }


    override public function getValue():Object {
        return ListPanel(_input).dataTable;
    }

    override public function set height(h:Number):void {
        super.height = h;
        ListPanel(_input).height = h;
    }

    public function addCollumn(label:String, path:String, editable:Boolean):void {
        ListPanel(_input).addCollumn(label,path,editable);
    }


    public function addCommand(cmd:Command):void {
        ListPanel(_input).addCommand(cmd);
    }}
}