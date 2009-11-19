package de.ama.framework.data {
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
        selections.push(new Selection(data));
    }

    public function addSelection(s:Selection):void {
        selections.push(s);
    }

    public function getSelections():Array {
        return selections;
    }

    public function getFirstSelection():Selection {
        if(selections.length>0){
           return selections[0];
        }

        return null;
    }

}
}