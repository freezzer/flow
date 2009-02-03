package server.actions;


public class LoginAction extends ServerAction {

    public void execute() {
//        User user = (User) getDataPort().getBean();
//        String userId = Environment.getUserService().login(user.getName(), user.getPwd());
//        getDataPort().setUserId(userId);
    }

    public boolean needsUser() {
        return false;
    }
}
