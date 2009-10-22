package de.ama.framework.command {
import de.ama.framework.gui.icons.IconStore;

import flash.ui.ContextMenuItem;

public class Command {

    public var label:String;
    public var icon:String;

    private var _contextMenuItem:ContextMenuItem=null;
    private var _context:CommandContext;


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

    public function set context(value:CommandContext):void {
        _context = value;
    }

    public function newContext():CommandContext {
  		_context = new CommandContext();
        return _context;
    }

    public function get context():CommandContext {
        return _context;
    }

    public function start():void{
        execute();
        _contextMenuItem = null;
    }

    protected function execute():void{

    }
}
}