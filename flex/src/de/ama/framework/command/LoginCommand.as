package de.ama.framework.command {
import de.ama.services.Environment;

public class LoginCommand extends Command{


    public function LoginCommand(label:String="anmelden",icon:String="lock") {
        super(label,icon);
    }


    override protected function execute():void {
        Environment.login(true);
    }
}
}