package de.ama.framework.command {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.LoadBoAction;
import de.ama.framework.data.Data;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.gui.frames.ApplicationPanel;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.framework.gui.frames.TreeEditor;

import de.ama.framework.util.Callback;
import de.ama.framework.util.Factory;

import mx.core.Application;

public class OpenEditorCommand extends Command{

    private var editor:TreeEditor = null;

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
        showEditor(Data(action.data));
    }

    private function showEditor(data:Data):void {
        editor.setData(data);   // null forces new empty data
        var cp:ApplicationPanel = Application.application.getContentPane();
        cp.addContent(editor);
    }

}
}