package de.ama.framework.gui.fields {
public interface GUIComponent {
    function set x(val:Number):void;
    function set y(val:Number):void;
    function set width(val:Number):void;
    function set height(val:Number):void;
    function set labelWidth(val:int):void;
    function setStyle(key:String, val:*):void;

    function get x():Number;
    function get y():Number;
    function get labelWidth():int;
    function get height():Number;
    function get width():Number;
    function getSourceCode(xml:Boolean):String;

}}