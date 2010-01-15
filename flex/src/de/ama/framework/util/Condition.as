package de.ama.framework.util {
[RemoteClass(alias="de.ama.framework.data.Condition")]

public class Condition {
    public static  var EQ   :String = " = ";
    public static  var LE   :String = " <= ";
    public static  var GE   :String = " >= ";
    public static  var LT   :String = " < ";
    public static  var GT   :String = " > ";
    public static  var LIKE :String = " like ";
    public static  var AND  :String = " AND ";
    public static  var OR   :String = " OR ";

    public var path:String;
    public var op:String;
    public var value:Object;
    public var concatOperator:String;
    public var negated:Boolean = false;
    public var children:Array = null;


    public function Condition(path:String=null, op:String=null, value:Object=null) {
        this.path = path;
        this.op = op;
        this.value = value;
    }

    public function and(c:Condition ): Condition {
        concatOperator=AND;
        addChild(c);
        return this;
    }

    public function or(c:Condition ): Condition {
        concatOperator=OR;
        addChild(c);
        return this;
    }

    private function addChild(c:Condition ):void{
        if(children==null){
           children = new Array();
        }
        children.push(c);
    }


}
}