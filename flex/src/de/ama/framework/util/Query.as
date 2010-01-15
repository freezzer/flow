package de.ama.framework.util {
[RemoteClass(alias="de.ama.framework.data.Query")]

public class Query {

    public var targetName:String;
    public var orderColumn:String;
    public var limit:Number = -1;
    public var negated:Boolean = false;
    public var condition:Condition;


    public function Query(targetName:String=null, condition:Condition=null, orderColumn:String=null, limit:Number=-1, negated:Boolean=false) {
        this.targetName = targetName;
        this.orderColumn = orderColumn;
        this.limit = limit;
        this.negated = negated;
        this.condition = condition;
    }

}
}