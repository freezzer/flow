package de.ama.framework.command {
import de.ama.framework.data.BusinessObject;
import de.ama.framework.gui.frames.ApplicationPanel;
import de.ama.framework.gui.frames.Editor;
import de.ama.services.Factory;

import mx.core.Application;
import mx.core.Container;

public class OpenEditorCommand_Copy extends Command{


    public function OpenEditorCommand_Copy(label:String="kopieren",icon:String="copy") {
        super(label,icon);
    }


    override protected function execute():void {
        var data:BusinessObject = getData(true).clone();

        var editor:Editor = Factory.createEditor(getProperty("editor"));

        var cp:ApplicationPanel = Application.application.getContentPane();
        cp.addContent(Container(editor));

        editor.setData(data);
    }
}
}