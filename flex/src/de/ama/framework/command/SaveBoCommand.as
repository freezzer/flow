package de.ama.framework.command {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.DataAction;
import de.ama.framework.action.SaveBoAction;
import de.ama.framework.util.Callback;

public class SaveBoCommand extends Command{


    public function SaveBoCommand(label:String="speichern",icon:String="save") {
        super(label,icon);
    }


    override protected function execute():void {
        var sa:SaveBoAction    = new SaveBoAction();
        sa.data = context.getData(true);
        ActionStarter.instance.execute(sa , new Callback(this, resulthandler ));
    }

    private function resulthandler(): void {

    }
}
}