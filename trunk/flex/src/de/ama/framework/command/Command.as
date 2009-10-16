package de.ama.framework.command {
public class Command {
    private var _context:CommandContext;


    public function get context():CommandContext {
        return _context;
    }

    public function set context(value:CommandContext):void {
        _context = value;
    }

    public function execute():void{

    }
}
}