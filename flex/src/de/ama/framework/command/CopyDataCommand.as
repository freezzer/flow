package de.ama.framework.command {
import de.ama.framework.data.Data;
import de.ama.framework.gui.frames.ApplicationPanel;
import de.ama.framework.gui.frames.TreeEditor;
import de.ama.framework.util.Factory;

import mx.core.Application;

public class CopyDataCommand extends Command{


    override public function execute():void {
        var data:Data = context.getData(true).clone();

        var e:TreeEditor = data.createEditor();
        e.label = context.getProperty("label",data.getName()+" Editor");
        e.setData(data);
        var cp:ApplicationPanel = Application.application.getContentPane();
        cp.addContent(e);
    }
}
}