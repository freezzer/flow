package de.ama.framework.action
{
[Event(name="broadcast", type="de.ama.framework.util.BroadcastEvent")]
[RemoteClass(alias="de.ama.framework.action.FileAction")]
public class FileAction extends ActionScriptAction {

    public var fileName:String;

}
}


