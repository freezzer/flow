package server.actions;


public class NewUserAction extends ServerAction {

    public void execute() {
//        User user = (User) getDataPort().getBean();
//        String userId = Environment.getUserService().newUser(user.getName(), user.getPwd());
//        commit();
//        getDataPort().setUserId(userId);
    }

    public boolean needsUser() {
        return false;
    }
}
