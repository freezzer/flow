package de.ama.framework.util {
import de.ama.framework.command.Command;
import de.ama.framework.data.Data;
import de.ama.framework.gui.frames.ListPane;
import de.ama.framework.gui.frames.TreeEditor;

public class Factory {

   private static var dictionary:Object = new Object();


   public static function register(key:String, c:Class):void{
       dictionary[key] = c;
   }

   public static function createData(key:String):Data{
       return Data(createObject(key));
   }

   public static function createObject(key:String):Object{
       var c:Class = dictionary[key];
       if(c!=null){
           return new c();
       }

       try {
           return Util.createObject(key);
       } catch(e:Error) {
       }

       throw Error("there is no Class for key ["+key+"] registered, and can not create by name ");

   }

    public static function registerCommand(key:String, c:Class):void{
        dictionary[key] = c;
    }

    public static function createCommand(type:String):Command {
        return Command(createObject(type));
    }}
}