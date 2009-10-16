package de.ama.framework.command {
import de.ama.framework.data.*;
import de.ama.framework.util.Util;

public class SelectionModel {
    private var selections:Array = new Array();
    private var condition:String;

    public function SelectionModel(data:Data=null) {
    	if(data!=null){
	        addSelection(data);
    	}
    }

    public function addSelection(data:Data):void {
        selections.push(data);
    }

    public function getSelections():Array {
        return selections;
    }

    public function getFirstSelection():Data {
        if(selections.length>0){
           return selections[0];
        }

        return null;
    }

}
}