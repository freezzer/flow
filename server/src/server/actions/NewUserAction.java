package server.actions;

import server.services.Environment;
import de.ama.util.StringDivider;


public class NewUserAction extends ServerAction {

    public void execute() {
        String tmp = (String) getData();
        StringDivider sd = new StringDivider(tmp,"{del}");
        if(sd.ok()){
            String userId = Environment.getUserService().newUser(sd.pre() ,sd.post());
            commit();
            setData(userId);
        }

    }

    public boolean needsUser() {
        return false;
    }
}
