package de.ama.framework.command {
import de.ama.services.Environment;

public class LogoutCommand extends Command{


    public function LogoutCommand(label:String="abmelden",icon:String="lock") {
        super(label,icon);
    }


    override protected function execute():void {
        Environment.logout();
    }
}
}