package de.ama.framework.data {
import de.ama.framework.util.Query;
import de.ama.framework.util.Util;
[RemoteClass(alias="de.ama.framework.data.SelectionModel")]
public class SelectionModel {
    public var selections:Array = new Array();
    private var _query:Query;
    public var type:String;

    public function SelectionModel(data:BusinessObject=null) {
    	if(data!=null){
	        addSelection(data);
    	}
    }

    public function addSelection(d:BusinessObject):void {
        selections.push(new BoReference(d));
        if( Util.isEmpty(type)){
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

    public function getQuery():Query {
        if(_query==null){
            _query = new Query(type);
        }
        return _query;
    }

}
}