package de.ama.framework.data {
	import de.ama.framework.util.Util;
	
public class Selection {
    public var className:String;
    public var oid:int;

    public function Selection(data:Data=null) {
    	if(data!=null){
           oid = data.oid;
           className  = Util.getClassName(data);
        }
    }

}
}