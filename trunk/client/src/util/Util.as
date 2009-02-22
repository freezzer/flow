package util
{
	public class Util
	{
		public static function saveToString(o:Object,def:String=""):String {
		    if(o==null)return def;
		    return o.toString();
		}
	}
}