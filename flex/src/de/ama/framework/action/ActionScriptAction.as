package de.ama.framework.action {
import de.ama.framework.data.SelectionModel;

[RemoteClass(alias="de.ama.framework.action.ActionScriptAction")]
public class ActionScriptAction {

    public var userSessionId:String;
    public var catalog:String;

    public var message:String;
    public var detailErrorMessage:String;
    public var selectionModel:SelectionModel;
    public var data:Object;

    public var versionMismatch:Boolean;
    public var dontCommit:Boolean;
    public var needsLogin:Boolean;


	public function addOidString(oidString:String):void{
		if(selectionModel==null){
		   selectionModel = new SelectionModel();
		}
		selectionModel.addOidString(oidString);
	}

}
}