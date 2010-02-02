package de.ama.framework.action {
import flash.utils.IDataInput;
import flash.utils.IDataOutput;
import flash.utils.IExternalizable;

[RemoteClass(alias="de.ama.framework.action.ActionTransporter")]
public class ActionTransporter implements IExternalizable{

    public var catalog:String;
    public var action:Object;


    public function ActionTransporter(catalog:String=null, action:Object=null) {
        this.catalog = catalog;
        this.action = action;
    }

    public function readExternal(input:IDataInput):void {
        catalog = input.readObject() as String;
        action = input.readObject() as ActionScriptAction;
    }

    public function writeExternal(output:IDataOutput):void {
        output.writeObject(catalog);
        output.writeObject(action);
    }
}
}