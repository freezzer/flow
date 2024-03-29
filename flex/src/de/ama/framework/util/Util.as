package de.ama.framework.util
{
import de.ama.framework.gui.frames.EditPanel;
import de.ama.framework.gui.frames.Editor;
import de.ama.framework.gui.frames.InfoDialog;

import flash.display.DisplayObject;
import flash.display.Sprite;
import flash.errors.IllegalOperationError;
import flash.utils.getDefinitionByName;
import flash.utils.getQualifiedClassName;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.core.Application;
import mx.core.ApplicationGlobals;
import mx.core.UIComponent;
import mx.managers.PopUpManager;
import mx.utils.ObjectUtil;

public class Util
{
    private static var ALPHA:String = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private static var NUM:String = "1234567890"
    private static var UML:String = "äöüÄÖÜ"


    private static var globalStore:Array = new Array();

    public static function saveToString(o:Object, def:String = ""):String {
        if (o == null)return def;
        return o.toString();
    }

    public static function shrinkString(str:String, l:int, pre:String = ""):String {
        if (str == null)return "";
        if (str.length > l) {
            return pre + str.substr(str.length - l);
        }
        return pre + str;
    }


    public static function findComponent(key:String):Object {
        return ApplicationGlobals.application.getChildByName(key);
    }

    public static function findParentEditPanel(comp:Object):EditPanel {
        if (comp == null) return null;
        if (comp is EditPanel) return EditPanel(comp);

        if (comp is DisplayObject) {
            if (comp.parent == null) return null;
            if (comp.parent is EditPanel) return EditPanel(comp.parent);
            return findParentEditPanel(UIComponent(comp.parent));
        }

        return null;
    }

    public static function findParentEditor(comp:Object):Editor {
        if (comp == null) return null;
        if (comp is Editor) return Editor(comp);

        if (comp is DisplayObject) {
            if (comp.parent == null) return null;
            if (comp.parent is Editor) return Editor(comp.parent);
            return findParentEditor(UIComponent(comp.parent));
        }

        return null;
    }

    public static function getComponent(key:String):Object {
        var c:Object = findComponent(key);
        if (c == null) {
            showError("Component for id(" + key + ") not found")
        }

        return c;
    }

    public static function showMessage(m:String):void {
        var dlg:InfoDialog = InfoDialog(PopUpManager.createPopUp(DisplayObject(Application.application), InfoDialog, true));
        dlg.msg = saveToString(m);
        //       dlg.currentState="info";
        PopUpManager.centerPopUp(dlg);
    }

    public static function showQuestion(m:String, parent:Sprite = null, f:Function = null):void {
        Alert.show(m, "Question", 3, parent, f);
    }

    public static function showError(m:String, details:String = null):void {
        var dlg:InfoDialog = InfoDialog(PopUpManager.createPopUp(DisplayObject(Application.application), InfoDialog, true));
        dlg.msg = saveToString(m);
        dlg.detailedMsg = saveToString(details);
        //        if(isEmpty(details)){
        //          dlg.currentState="info";
        //        }
        PopUpManager.centerPopUp(dlg);
    }


    public static function storeGlobal(key:String, val:Object):void {
        globalStore[key] = val;
    }

    public static function getGlobal(key:String):Object {
        return globalStore[key];
    }

    public static function mapProperties(src:Object, dst:Object):void {
        var info:Object = ObjectUtil.getClassInfo(src);

        for each(var key:String in info.properties) {
            try {
                dst[key] = src[key];
            } catch(e:Error) {
                // macht nichts
            }
        }
    }

    public static function divideString(str:String, del:String):Array {
        var ret:Array = new Array();
        var pos:int = str.indexOf(del);
        if (pos >= 0) {
            ret.push(str.substring(0, pos));
            ret.push(str.substring(pos + 1));
        } else {
            ret.push(str);
        }
        return ret;
    }

    public static function getObjectProperty(obj:Object, path:String, def:String = ""):String {
        var object:Object = getObjectValue(obj, path);
        return object == null ? def : object.toString();
    }

    public static function getObjectValue(obj:Object, path:String):Object {
    	if(isEmpty(path) || isEqual(".",path) ){
    		return obj;
    	}
    	
        var div:Array = divideString(path, ".");
        if (div.length > 1) {
            var child:Object = getObjectValue(obj, div[0]);
            if (child != null) {
                return getObjectValue(child, div[1]);
            }
        }

        try {
            return obj[path];
        } catch(e:Error) {
            Util.showError("getObjectValue failed for path "+path+" in "+getUnqualifiedClassName(obj), e.getStackTrace());
        }
        return null;
    }

    public static function setObjectValue(obj:Object, path:String, value:Object):void {

        var div:Array = divideString(path, ".");
        if (div.length > 1) {
            var child:Object = getObjectValue(obj, div[0]);
            if (child != null) {
                return setObjectValue(child, div[1], value);
            }
        }

        try {
            obj[path] = value;
        } catch(e:Error) {
        	Util.showError("setObjectValue failed for path "+path+" in "+getUnqualifiedClassName(obj), e.getStackTrace());
        }
    }

    public static function getClass(obj:Object):Class {
        return obj.constructor;
    }

    public static function classForName(name:String):Class {
        return getDefinitionByName(name) as Class;
    }

    public static function getClassName(o:Object):String {
        if (o == null) return "no classname for 'null'";
        if(o.hasOwnProperty("className")) {
            return o["className"];
        }
        return getQualifiedClassName(o);
    }

    public static function getUnqualifiedClassName(o:Object):String {
        if (o == null) return "no classname for 'null'";
        var name:String = getClassName(o);
        return name.split("::")[1];
    }


    public static function createObject(name:String):Object {
        var c:Class = classForName(name);
        return new c();
    }

    public static function isEmpty(val:String):Boolean {
        if (val == null) return true;
        if (val.length == 0) return true;
        return false;
    }

    public static function isEqual(a:*, b:*, caseInsensitive:Boolean = false):Boolean {
        if (a == null && b == null) return true;

        if (a == null && b != null) return false;
        if (a != null && b == null) return false;

        if (a is String) {
            return ObjectUtil.stringCompare(a, b, caseInsensitive) == 0;
        }
        if (a is Date) {
            return ObjectUtil.dateCompare(a, b) == 0;
        }
        if (a is Number) {
            return ObjectUtil.numericCompare(a, b) == 0;
        }


        return false;
    }

    public static function isUml(inputValue:String): String {
        return containsOnlyChars(inputValue, UML);
    }

    public static function isAlpha(inputValue:String): String {
        return containsOnlyChars(inputValue, ALPHA);
    }

    public static function isAlphaNum(inputValue:String): String {
        return containsOnlyChars(inputValue, ALPHA + NUM);
    }

    public static function isAlphaNumUml(inputValue:String): String {
        return containsOnlyChars(inputValue, ALPHA + NUM + UML);
    }

    public static function containsOnlyChars(inputValue:String, allowed:String): String {
        var i:int = 0;
        var tmp:String;
        for (i = 0; i < inputValue.length; i++) {
            tmp = inputValue.charAt(i);
            if (allowed.indexOf(tmp) < 0) {
                return tmp;
            }
        }
        return null;
    }

    public static function containsChars(inputValue:String, chars:String): Boolean {
        return containsForbiddenChars(inputValue, chars) != null;
    }

    public static function containsForbiddenChars(inputValue:String, forbidden:String): String {
        var i:int = 0;
        var tmp:String;
        for (i = 0; i < forbidden.length; i++) {
            tmp = forbidden.charAt(i);
            if (inputValue.indexOf(tmp) >= 0) {
                return tmp;
            }
        }
        return null;
    }

    public static function concat(ac:ArrayCollection, obj:Object): ArrayCollection {

        if (obj is ArrayCollection) {
            var col:ArrayCollection = ArrayCollection(obj);
            for (var i:int = 0; i < col.length; i++) {
                ac.addItem(col.getItemAt(i));
            }
        } else if (obj is Array) {
            for (var x:int = 0; x < obj.length; i++) {
                ac.addItem(obj[x]);
            }
        } else {
            throw IllegalOperationError("wrong parameter in Util.concat");
        }

        return ac;
    }

    public static function createArray(type:String, size:int = 0): Array {
        var ret:Array = new Array();
        for (var i:int = 0; i < size; i++) {
            ret.push(createObject(type));
        }
        return ret;
    }

    public static function firstCharToUpper(str:String):String {
        return str.charAt(0).toUpperCase() + str.substr(1);
    }

    public static function firstCharToLower(str:String):String {
        return str.charAt(0).toLowerCase() + str.substr(1);
    }

    public static function asString(o:Object):String {
        if(o==null) return "";
        if(o is String) return String(o);
        if(o is Date) return (o as Date).toLocaleDateString();
        if(o is Number) return Number(o).toString();
        return getUnqualifiedClassName(o);
    }
    
    public static function toHourMinutesString(minutes:int, offset:int=0):String {
    	var h:int = Math.floor(minutes/60);
    	var m:int = minutes - (h*60);
    	if(offset>0){
    		offset = Math.floor(offset/60);
    	}
    	return ""+(offset+h)+":"+ (m > 9 ? m : "0"+m);
    }

    public static function toMinutes(str:String):int {
    	if(isEmpty(str)) return 0;
    	if(str.indexOf(":")>=0){
	        var arr:Array = str.split(":");
	        var hours:int = int(arr[0]);
	        var minutes:int = int(arr[1]);
	        return hours*60+minutes;
	    }
	    return int(str);
    }



}
}