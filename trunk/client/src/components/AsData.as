package components
{
	public class AsData {	

	public var oidString:String;
	public var version:int;
		
		public function saveToString(o:Object,def:String=""):String {
		    if(o==null)return def;
		    return o.toString();
		}
		
		public function getValue(key:String):Object{
			return this[key];		}
		
		public function setValue(key:String, val:Object):void{
			this[key] = val;
		}

	}
	
}