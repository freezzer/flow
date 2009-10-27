package de.ama.framework.command {
import de.ama.framework.data.Data;
import de.ama.framework.gui.frames.ApplicationPanel;
import de.ama.framework.gui.frames.TreeEditor;
import de.ama.framework.util.Factory;

import mx.core.Application;

public class CopyDataCommand extends Command{


    public function CopyDataCommand(label:String="kopieren",icon:String="copy") {
        super(label,icon);
    }


    override protected function execute():void {
        var data:Data = getData(true).clone();

        var editor:TreeEditor = Factory.createEditor(getProperty("editor"));
        editor.label = label;

        editor.setData(data);
        var cp:ApplicationPanel = Application.application.getContentPane();
        cp.addContent(editor);
    }
}
}