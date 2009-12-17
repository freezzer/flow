package de.ama.framework.util {
[RemoteClass(alias="de.ama.framework.data.Condition")]

public class Condition {
    public static final var EQ   :String = " = ";
    public static final var LE   :String = " <= ";
    public static final var GE   :String = " >= ";
    public static final var LT   :String = " < ";
    public static final var GT   :String = " > ";
    public static final var LIKE :String = " like ";
    public static final var AND  :String = " AND ";
    public static final var OR   :String = " OR ";

    public var path:String;
    public var op:String;
    public var value:Object;
    public var concatOperator:String;
    public var negated:Boolean = false;
    public var children:Array = null;


    public function Condition(path:String, op:String, value:Object) {
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