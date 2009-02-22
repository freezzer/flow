package util
{
	import flash.utils.getQualifiedClassName;
	
	public var oidString:String;
	public var version:int;
		
	public class AsData {	
	}
	
	public function label() : String {
		return getQualifiedClassName(this);
	}
}