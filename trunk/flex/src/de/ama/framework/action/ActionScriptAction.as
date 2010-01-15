package de.ama.framework.action {
import de.ama.framework.command.Command;
import de.ama.framework.data.SelectionModel;
[RemoteClass(alias="de.ama.framework.action.ActionScriptAction")]
public class ActionScriptAction {

    public var actionId:int;
    public var userSessionId:String;
    public var catalog:String;
    public var eventName:String;

    public var message:String;
    public var detailErrorMessage:String;
    public var selectionModel:SelectionModel;
    public var data:Object;

    public var versionMismatch:Boolean;
    public var dontCommit:Boolean;
    public var needsLogin:Boolean = true;

  	public function set user(d:Object):void{}; // nur für streaming


    public function readCommand(c:Command):void {
        this.selectionModel = c.selectionModel;
        this.eventName = c.getProperty("eventName");
    }

}
}