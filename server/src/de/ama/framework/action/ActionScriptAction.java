package de.ama.framework.action;

import de.ama.framework.data.*;
import de.ama.services.Environment;
import de.ama.services.user.User;
import de.ama.util.Util;
import de.ama.db.Query;

import java.util.Collection;


/**
 * User: x
 * Date: 28.04.2008
 *

 *
 */
public class ActionScriptAction implements java.io.Serializable {

    // ************** ActionScript Members *********************

    public String userSessionId;
    public String catalog;

    public SelectionModel selectionModel;
    public Object data;
    public String message;
    public String detailErrorMessage;
    public boolean needsLogin;

    // *********************************************************

    private static ThreadLocal currentActionHolder;
    private transient User user;

    private boolean versionMismatch;
    private boolean dontCommit;

    public ActionScriptAction() {
    }


    public static void setCurrent(ActionScriptAction a) {
        if (currentActionHolder == null) {
            currentActionHolder = new ThreadLocal();
        }
        currentActionHolder.set(a);
    }

    public static ActionScriptAction getCurrent() {
        return (ActionScriptAction)currentActionHolder.get();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void execute() throws Exception {
    }

    public String getCatalog(){
        return catalog;
    }

    public void commit(){
        Environment.getPersistentService().commit();
    }

    public void rollback(){
        Environment.getPersistentService().rollback();
    }

    public String getName() {
        return Util.getUnqualifiedClassName(getClass());
    }


    public void setMessage(String m) {
        message = m;
    }

    public void mapBoToData(Object bo, Data data)  {
        data.getMapper().readDataFromBo(bo, data, DataMapper.FULL_OBJECT);
    }


    public DataTable mapBosToDataTable(Collection bos, Data data){
        return data.getMapper().createFromBoList(data,bos,false);
    }


     /**
     * transferiert die Daten eines Data-Graphen in den Bo-Graphen.
     * Wichtig ! Um Auskunft �ber Versionsmismatch zu bekommen, mu� nach dem save() �ber die
     * Methode hasVersionMismatch() kontrolliert werden ob ein Versionsmismatch vorlag.
     * @param data ,das gemapped werden soll
     * @return ein refeshter Data_Graph.
     */

    public Data storeData(Data data)  {
         try {
             Object o = mapDataToBo(data);
             Environment.getPersistentService().makePersistent(o);
             commit();
             Data ret = reloadData(data.getClass(),o);
             return ret;
         } catch (Exception e) {
             rollback();
             throw new RuntimeException("storeData failed ", e);
         }
     }

    public Object mapDataToBo(Data data)  {
        versionMismatch = false;

        if (data == null) {
            throw new RuntimeException("NO DATA to map to BO");
        }

        Object obj = null;

        String msg = data.getBoClassName() + " " + data.getOidString();
        if (data.isNew()) {
            obj = data.createEmptyBo();
            if (obj == null) {
                throw new RuntimeException("could not CREATE BO : " + msg);
            }
        } else {
            obj = Environment.getPersistentService().getObject(data.getOidString());
            if (obj == null) {
                throw new RuntimeException("could not FIND BO TO UPDATE : " + msg);
            }
        }

        try {

            DataMapper mapper = data.getMapper();
            mapper.checkVersion(obj, data);
            mapper.writeDataToBo(obj, data);
            return obj;

        } catch (MappingException me) {

            // SonderFall MappingException mit Konkurierendem Zugriff.
            if (me.hasError(MappingException.OPTIMISTIC_LOCKING_INVALID)) {
                versionMismatch = true;
            }

            throw new RuntimeException("MAPPING EXCEPTION : ", me);
        }

    }

    public Data reloadData(Class dataType, Object obj)  {
        try {
            Object o = Environment.getPersistentService().getObject(getOid(obj));
            Data data = Data.createEmptyData(dataType);
            data = data.getMapper().readDataFromBo(o, data, DataMapper.FULL_OBJECT);
            return data;
        } catch (Exception e) {
            rollback();
            throw new RuntimeException("could not reloadData Bo from DB <" + dataType.getName() + ">  ",e);
        }
    }

    private String getOid(Object obj) {
        return Environment.getPersistentService().getOidString(obj);
    }

    public Object getBo(Data data) {
        return getBo(data.getOidString());
    }

    public Object getBo(Selection s) {
        return Environment.getPersistentService().getObject(new Query(Util.createClass(s.getClassName()),"oid",Query.EQ, s.getOid()),true);
    }

    public Object getBo(String oidString) {
        return Environment.getPersistentService().getObject(oidString);
    }

    public  void refreshObject(Object toRefresh) {
        if (dontCommit) {
//            if(TRACE.ON()){ TRACE.add(this,"refreshObject: dontCommit=true !"); }
            return;
        } else {
            Environment.getPersistentService().refresh(toRefresh);
        }
    }
}
