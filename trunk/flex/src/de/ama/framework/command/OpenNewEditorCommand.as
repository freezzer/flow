package de.ama.framework.command {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.LoadBoAction;
import de.ama.framework.data.Data;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.gui.frames.ApplicationPanel;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.framework.gui.frames.Editor;

import de.ama.framework.util.Callback;
import de.ama.services.Factory;

import mx.core.Application;
import mx.core.Container;

public class OpenNewEditorCommand extends Command{

    private var editor:Editor = null;

    public function OpenNewEditorCommand(label:String="Neu",icon:String="new") {
        super(label,icon);
    }


    override protected function execute():void {

        editor = Factory.createEditor(getProperty("editor"));
        var cp:ApplicationPanel = Application.application.getContentPane();
        cp.addContent(Container(editor));
        editor.setData(null);   // null forces new empty data
    }


}
}