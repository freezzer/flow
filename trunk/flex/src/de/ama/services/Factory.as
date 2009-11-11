package de.ama.services {
import de.ama.framework.util.*;
import de.ama.framework.command.*;
import de.ama.framework.data.Data;
import de.ama.framework.gui.frames.*;
import de.ama.services.permission.*;

public class Factory {

    private static var bean_dictionary:Object = new Object();
    private static var panel_dictionary:Object = new Object();
    private static var editor_dictionary:Object = new Object();
    private static var lister_dictionary:Object = new Object();
    private static var command_dictionary:Object = new Object();
    private static var permission_dictionary:Object = new Object();


    private static function register(dictionary:Object, key:String, c:Class):void {
        if(dictionary[key]!=null){
            throw Error("de.ama.services.Factory: duplicate key ["+key+"] in dictionary "+dictionary);
        }
        dictionary[key] = c;
    }

    private static function createClass(dictionary:Object, key:String):Class {
        return dictionary[key];
    }

    private static function createObject(dictionary:Object, key:String):Object {
        var c:Class = dictionary[key];
        if (c != null) {
            return new c();
        }

        try {
            return Util.createObject(key);
        } catch(e:Error) {
        }

        throw Error("there is no Class for key [" + key + "] registered, and can not create by name ");

    }

    /////////////////////// Beans //////////////////////////////////////

    public static function registerBean(key:String, c:Class):void {
        register(bean_dictionary,key,c);
    }

    public static function createBean(key:String):Data {
        return Data(createObject(bean_dictionary,key));
    }

    public static function createBeanClass(key:String):Class {
        return createClass(bean_dictionary,key);
    }

    /////////////////////// Comands //////////////////////////////////////

    public static function registerCommand(key:String, c:Class):void {
        register(command_dictionary,key,c);
    }

    public static function createCommand(type:String):Command {
        return Command(createObject(command_dictionary, type));
    }

    /////////////////////// Panels //////////////////////////////////////

    public static function registerPanel(key:String, c:Class):void {
        register(panel_dictionary,key,c);
    }

    public static function createPanel(name:String):EditPanel {
        var c:Class = panel_dictionary[name];
        if (c != null) {
            return EditPanel(new c());
        }

        var ret:EditPanel = new EditPanel();  // DefaultPanel
        ret.generic = true;
        return ret;
    }

    /////////////////////// Listers /////////////////////////////////////

    public static function registerLister(name:String, c:Class):void {
        register(lister_dictionary,name,c);
    }

    public static function createLister(name:String):ListPanel {
        var c:Class = lister_dictionary[name];
        if (c != null) {
            return ListPanel(new c());
        }

        var ret:ListPanel = new ListPanel();  // DefaultPanel
        ret.generic = true;
        return ret;
    }

    /////////////////////// Editors /////////////////////////////////////

    public static function registerEditor(key:String, c:Class):void {
        register(editor_dictionary,key,c);
    }

    public static function createEditor(type:String):Editor {
        return Editor(createObject(editor_dictionary,type));
    }

    /////////////////////// Permissions //////////////////////////////////////

    public static function registerPermission(key:String, c:Class):void {
        register(permission_dictionary,key,c);
    }

    public static function createPermission(key:String):PermissionContext {
        return PermissionContext(createObject(permission_dictionary,key));
    }


}
}