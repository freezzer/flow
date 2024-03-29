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

    public function get listPanel():ListPanel {
        return ListPanel(_input);
    }


    override public function set labelWidth(w:int):void {
    }

    public function setListPanel(lp:ListPanel):void {
        if (_input != null) {
            removeChild(_input);
        }
        lp.label = _labelText;
        lp.searchEnabled = false;
        _input = lp;
        _input.addEventListener(MouseEvent.CLICK, onClick);
        _input.styleName="ListField";
        addChild(_input);
        layout();
    }


    override public function layout():void {
        listPanel.toolbarSize = CommandButton.SMALL;
    }

    public override function setValue(val:Object):void {
        ListPanel(_input).setDataTable(val as Array);
    }

    override public function initDefaultValue():void {
    }
    
    override public function getValue():Object {
        return ListPanel(_input).getDataTable();
    }

    override public function set height(h:Number):void {
        super.height = h;
        listPanel.height = h;
    }

    public function addCollumn(type:String,label:String, path:String, editable:Boolean):void {
        ListPanel(_input).addCollumn(type,label, path, editable,false);
    }


    public function addCommand(cmd:Command):void {
        ListPanel(_input).addCommand(cmd);
    }

    override public function getSourceCode(xml:Boolean):String {
        if(xml){
            return "<input x=\"" + x + "\" y=\"" + y + "\" w=\"" + width + "\" h=\"" + height + "\" label=\"" + label + "\" path=\"" + localpath + "\" type=\"list\" >";
        } else {
           return "insertListField(\""+label+"\",\""+localpath+"\",\""+Util.getUnqualifiedClassName(_input)+"\","+x+","+y+","+width+","+height+");";
        }
    }

}
}