package de.ama.framework.data {
import de.ama.framework.util.Util;
[RemoteClass(alias="de.ama.framework.data.SelectionModel")]
public class SelectionModel {
    public var selections:Array = new Array();
    public var type:String;

    public function SelectionModel(data:BusinessObject=null) {
    	if(data!=null){
	        addSelection(data);
    	}
    }

    public function addSelection(d:BusinessObject):void {
        selections.push(new BoReference(d));
        if(selections.length==1 || Util.isEmpty(type)){
        	type =  Util.getClassName(d);
        }
    }

    public function getSelections():Array {
        return selections;
    }

    public function getFirstSelection():BoReference {
        if(selections.length>0){
           return selections[0];
        }

        return null;
    }

}
}