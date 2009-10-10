package de.ama.framework.data {
import de.ama.framework.util.Util;

public class Data{
    public var oidString:String;
    public var version:int;

    public function readProperties(src:Object):void{
        Util.mapProperties(src,this);
    }

    public function writeProperties(dst:Object):void{
        Util.mapProperties(this,dst);
    }
}
}