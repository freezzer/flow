package de.ama.framework.command {
import de.ama.framework.gui.frames.ApplicationPanel;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.framework.util.Factory;

import mx.core.Application;

public class OpenListerCommand extends Command{

    public function OpenListerCommand(label:String="Auflister",icon:String="table") {
        super(label,icon);
    }


    override protected function execute():void {

        var lp:ListPanel = Factory.createLister(getProperty("lister"))
        lp.label = label;
        lp.useToolbar = 1;

        var cp:ApplicationPanel = Application.application.getContentPane();
        cp.addContent(lp);

        lp.reload();

    }
}
}