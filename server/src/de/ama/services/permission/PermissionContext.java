/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/

package de.ama.services.permission;

import java.util.ArrayList;
import java.util.List;

public class PermissionContext implements java.io.Serializable , de.ama.db.PersistentMarker {

    private boolean permitted = true;
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

    private List<PermissionSwitch> switches = new ArrayList<PermissionSwitch>();
    public List<PermissionSwitch> getSwitches() {   return switches; }
    public void setSwitches(List<PermissionSwitch> switches) {   this.switches = switches;   }

    public boolean isPermitted(String key){
        for (int i = 0; i < switches.size(); i++) {
            PermissionSwitch s = switches.get(i);
            if(s.key.equals(key)){
                return s.isOn;
            }
        }
        return false;
    }

    protected void addSwitches(){
    }

    public void onAfterLoad()  {
         addSwitches();
    }

    protected void add(PermissionSwitch ps){
       if(!switches.contains(ps)){
           switches.add(ps);
       }
    }


}
