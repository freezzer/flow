package de.ama.framework.data {

public class SelectionModel {
    public var selections:Array = new Array();
    public var condition:String;
    public var type:String;

    public function SelectionModel(data:Data=null) {
    	if(data!=null){
	        addSelection(data);
    	}
    }

    public function addSelection(data:Data):void {
        selections.push(new Selection(data));
    }

    public function addOidString(oidString:String):void {
    	var s:Selection = new Selection();
    	s.oidString = oidString;
        selections.push(s);
    }

}
}