package de.ama.framework.util {
import de.ama.framework.data.Data;
import de.ama.framework.gui.frames.ListPane;
import de.ama.framework.gui.frames.TreeEditor;

public class Factory {

   private static var dictionary:Object = new Object();


   public static function register(key:String, c:Class):void{
       dictionary[key] = c;
   }

   public static function createObject(key:String):Object{
       var c:Class = dictionary[key];
       if(c!=null){
           return new c();
       }
       throw Error("there is no Class for key ["+key+"] registered in Factory");
   }

    public static function createListPane(dataType:String, data:Data = null):ListPane {
//        if (data == null) {
//            data = Data(createObject(dataType));
//        }

        var ret:ListPane = new ListPane();
        //ret.setData(data);

        return ret;
    }
    public static function createEditor(dataType:String, data:Data = null):TreeEditor {
        if (data == null) {
            data = Data(createObject(dataType));
        }

        var ret:TreeEditor = data.createEditor();
        ret.setData(data);

        return ret;
    }

}
}