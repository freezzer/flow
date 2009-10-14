package de.ama.framework.util {

public class Factory {

   private static var dictionaty:Object = new Object();


   public function register(key:String, c:Class):void{
       dictionaty[key] = c;
   }

   public function createObject(key:String):Object{
       var c:Class = dictionaty[key];
       if(c!=null){
           return new c();
       }
   }

}
}