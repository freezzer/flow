package de.ama.framework.command {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.LoadBoAction;
import de.ama.framework.data.Data;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.util.Callback;

import mx.collections.ArrayCollection;

public class SelectBoCommand extends Command{


    public function SelectBoCommand(label:String="auswaehlen",icon:String="search") {
        super(label,icon);
    }

    override protected function execute():void {
        var table:ArrayCollection = LookupCache.getTable(selectionModel);
        

    }

}
}