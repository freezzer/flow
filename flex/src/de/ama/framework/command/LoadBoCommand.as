package de.ama.framework.command {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.LoadBoAction;
import de.ama.framework.data.Data;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.util.Callback;

public class LoadBoCommand extends Command{


    public function LoadBoCommand(label:String="laden",icon:String="refresh") {
        super(label,icon);
    }


    override protected function execute():void {
        var sa:LoadBoAction    = new LoadBoAction();
        sa.selectionModel = selectionModel;
        ActionStarter.instance.execute(sa , new Callback(this, resulthandler ));
    }

    private function resulthandler(action:LoadBoAction): void {
        invoker.setData(Data(action.data));
    }
}
}