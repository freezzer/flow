package de.ama.framework.command {
import flash.ui.ContextMenuItem;

public class Command {

    public var label:String;
    public var contextMenuItem:ContextMenuItem=null;
    private var _context:CommandContext;


    public function Command(label:String="") {
        this.label = label;
    }

    public function get context():CommandContext {
    	if(_context==null){
    		_context = new CommandContext();
    	}
        return _context;
    }

    public function execute():void{

    }
}
}