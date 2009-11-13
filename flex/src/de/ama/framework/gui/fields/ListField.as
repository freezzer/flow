package de.ama.framework.gui.fields {
import de.ama.framework.command.Command;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.framework.util.Util;
import de.ama.services.Factory;

import flash.events.MouseEvent;

public class ListField extends EditField{
    private var _labelText:String;

    public function ListField(caption:String = "ListField", path:String = null, lister:String = null) {
        super(caption, path);
        if (!Util.isEmpty(lister)) {
            setListPanel(Factory.createLister(lister));
        }
    }

    public override function createInput():void {
        if (_input == null) {
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

    public function setListPanel(lp:ListPanel):void {
        if (_input != null) {
            removeChild(_input);
        }
        lp.toolbarSize = CommandButton.SMALL;
        lp.label = _labelText;
        _input = lp;
        _input.addEventListener(MouseEvent.CLICK, onClick);
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
        ListPanel(_input).addCollumn(label, path, editable);
    }


    public function addCommand(cmd:Command):void {
        ListPanel(_input).addCommand(cmd);
    }

    override public function getXmlSourceCode():String {
        return "<input x=\"" + x + "\" y=\"" + y + "\" w=\"" + width + "\" h=\"" + height + "\" label=\"" + label + "\" path=\"" + localpath + "\" type=\"list\" >";
    }

}
}