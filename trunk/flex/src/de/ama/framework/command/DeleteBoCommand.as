package de.ama.framework.command {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.DeleteBoAction;
import de.ama.framework.util.Callback;

public class DeleteBoCommand extends Command{


    public function DeleteBoCommand(label:String="laden",icon:String="refresh") {
        super(label,icon);
    }


    override protected function execute():void {
        var sa:DeleteBoAction  = new DeleteBoAction();
        sa.readCommand(this);
        ActionStarter.instance.execute(sa , new Callback(this, resulthandler ));
    }

    private function resulthandler(action:DeleteBoAction): void {
        invoker.setData(null);
    }
}
}