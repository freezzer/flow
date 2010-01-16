
package de.ama.framework.gui.frames {
import de.ama.framework.data.BusinessObject;

import flash.display.DisplayObject;

import mx.containers.Canvas;
import mx.containers.VBox;

public class PanelEditor  extends Editor {
    private var _contenPanel:Canvas ;
    private var _editPanel:EditPanel ;

    public function PanelEditor() {
        var vbox:VBox = new VBox();
        vbox.percentWidth=100;
        vbox.percentHeight=100;
        super.addChild(vbox);
        
        _contenPanel = new Canvas();
        _contenPanel.styleName = "PanelEditorCanvas";
        _contenPanel.percentWidth=100;
        _contenPanel.percentHeight=100;
        vbox.addChild(_contenPanel);

        buttonbar = CommandButtonBar(vbox.addChild(new CommandButtonBar()));
        buttonbar.setStyle("horizontalAlign","right");
        buttonbar.setStyle("verticalAlign","middle");
        buttonbar.invoker = this;
        vbox.addChild(buttonbar);

        onCreationComplete();
    }


    override public function addChild(child:DisplayObject):DisplayObject {
        if(child is EditPanel){
            _editPanel = EditPanel(child);
            _contenPanel.addChild(child);
        } else {
            throw new TypeError("PanelEditor accepts only EditPanel as child");
        }

        return _editPanel;
    }

    override public function setData(data:BusinessObject):void {
    	if(data==null){
    	   data = createData();
    	}
        _editPanel.setData(data);
    }

    override public function getData():BusinessObject {
        return _editPanel.getData();
    }
}
}

