package de.ama.framework.command {
import de.ama.framework.data.Data;
import de.ama.framework.gui.frames.ApplicationPanel;
import de.ama.framework.gui.frames.ListPane;
import de.ama.framework.util.Factory;

import mx.core.Application;

public class OpenListerCommand extends Command{


    override public function execute():void {
        var data:Data = context.getData(true);

        var l:ListPane = new ListPane();
        l.label = context.getProperty("label",data.getName()+" Auflister");
        var cp:ApplicationPanel = Application.application.getContentPane();
        cp.addContent(l);

    }
}
}