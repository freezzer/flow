package praxis.view.kalender {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.command.Command;
import de.ama.framework.util.Callback;

public class SaveCalendarCommand extends Command{

    public function SaveCalendarCommand(label:String="speichern",icon:String="save") {
        super(label,icon);
    }


    override protected function execute():void {
        var sa:CalendarAction    = new CalendarAction();
        sa.data = CalendarEditor(invoker).getCalendarEntries();
        sa.readCommand(this);
        sa.type = CalendarAction.SAVE;
        ActionStarter.instance.execute(sa , new Callback(this, resulthandler ));
    }

    private function resulthandler(action:CalendarAction): void {
        CalendarEditor(invoker).setCalendarEntries(Array(action.data));

    }
}
}