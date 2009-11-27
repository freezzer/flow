package de.ama.framework.command {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.LoadBoAction;
import de.ama.framework.data.BusinessObject;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.gui.frames.ApplicationPanel;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.framework.gui.frames.Editor;

import de.ama.framework.util.Callback;
import de.ama.services.Factory;

import mx.core.Application;
import mx.core.Container;

public class OpenEditorCommand extends Command{

    private var editor:Editor = null;

    public function OpenEditorCommand(label:String="Bearbeiten",icon:String="edit") {
        super(label,icon);
    }


    override protected function execute():void {

        editor = Factory.createEditor(getProperty("editor"));

        if(selectionModel.getFirstSelection()!=null){
            var sa:LoadBoAction    = new LoadBoAction();
            sa.selectionModel = selectionModel;
            ActionStarter.instance.execute(sa , new Callback(this, resulthandler ));
        } else {
            showEditor(null);
        }

    }

    private function resulthandler(action:LoadBoAction): void {
        showEditor(BusinessObject(action.data));
    }

    private function showEditor(data:BusinessObject):void {
        var cp:ApplicationPanel = Application.application.getContentPane();
        cp.addContent(Container(editor));

        editor.setData(data);   // null forces new empty data
    }

}
}