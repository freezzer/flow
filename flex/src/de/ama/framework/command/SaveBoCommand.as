package de.ama.framework.command {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.SaveBoAction;
import de.ama.framework.data.BusinessObject;
import de.ama.framework.util.Callback;

public class SaveBoCommand extends Command{


    public function SaveBoCommand(label:String="speichern",icon:String="save") {
        super(label,icon);
    }


    override protected function execute():void {
        var sa:SaveBoAction    = new SaveBoAction();
        sa.data = invoker.getData();
        sa.readCommand(this);
        ActionStarter.instance.execute(sa , new Callback(this, resulthandler ));
    }

    private function resulthandler(action:SaveBoAction): void {
        invoker.setData(BusinessObject(action.data));
    }
}
}