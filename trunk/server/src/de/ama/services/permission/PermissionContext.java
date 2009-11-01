/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/

package de.ama.services.permission;

public class PermissionContext implements java.io.Serializable , de.ama.db.PersistentMarker {

    private boolean permitted;
    public  boolean getPermitted() {  return permitted; }
    public  void   setPermitted(boolean in) {  permitted=in; }

    private String userId;
    public  String getUserId() { return de.ama.util.Util.saveToString(userId); }
    public  void   setUserId(String in) {  userId=in; }

    private String userName;
    public  String getUserName() { return de.ama.util.Util.saveToString(userName); }
    public  void   setUserName(String in) {  userName=in; }

    private String context;
    public  String getContext() { return de.ama.util.Util.saveToString(context); }
    public  void   setContext(String in) {  context=in; }

}
