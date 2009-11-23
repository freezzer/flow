package de.ama.framework.data {
import de.ama.framework.util.Util;

public class SelectionModel {
    public var selections:Array = new Array();
    public var condition:String;
    public var type:String;

    public function SelectionModel(data:Data=null) {
    	if(data!=null){
	        addData(data);
    	}
    }

    public function addData(data:Data):void {
        addSelection(data);
    }

    public function addSelection(d:Data):void {
        selections.push(d);
        if(selections.length==1 || Util.isEmpty(type)){
        	type =  Util.getClassName(d);
        }
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