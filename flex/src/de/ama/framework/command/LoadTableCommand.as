package de.ama.framework.command {
import de.ama.framework.gui.fields.ProxyField;
import de.ama.framework.gui.frames.ListPanel;

public class LoadTableCommand extends Command{


    public function LoadTableCommand(label:String="laden",icon:String="refresh") {
        super(label,icon);
    }


    override protected function execute():void {
    	if( invoker is ListPanel){
    		var lp:ListPanel = invoker as ListPanel;
			lp.reload();    		
    	}
    }

}
}