package praxis.view.kalender {
import de.ama.framework.action.ActionScriptAction;

[RemoteClass(alias="praxis.action.CalendarAction")]
public class CalendarAction extends ActionScriptAction{
    public static var SAVE:int = 0
    public static var LOAD_DAY:int = 1
    public static var LOAD_WEEK:int = 2

    public var type:int = SAVE;
    public var date:Date = null;

}
}