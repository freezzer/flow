package de.ama.framework.data;

import de.ama.db.Embeded;
import de.ama.db.Persistent;
import de.ama.services.Environment;
import de.ama.util.StringDivider;
import de.ama.util.Util;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 26.11.2009
 * Time: 21:10:56
 * To change this template use File | Settings | File Templates.
 */
public class BoReference<C> implements Embeded, java.io.Serializable{
    public long oid;
    public String type;
    private transient C bo;

    public boolean hasReference(){
        if(Util.isEmpty(type)) return false;
        if(oid<=0) return false;
        return true;
    }

    public long getOid() {
        return oid;
    }

    public Class getType() {
        Class ret = null;
        if(Util.isEmpty(type)) return null;
        if(type.indexOf(".")<0){
            try {
                ret = Environment.getBeanClass(type);
            } catch (Exception e) {
                ret=null;
            }
        }
        if(ret==null){
            ret = Util.createClass(type.replace("::","."));
        }
        
        return ret;
    }

    public C getBo() {
        if(bo==null && hasReference()){
           Query q=new Query(getType() , new Condition("oid", Condition.EQ, getOid()));
           bo =  (C) Environment.getPersistentService().getObject(q,true );
        }
        return bo;
    }

    public void setBo(C bo) {
        if (bo instanceof Persistent) {
            Persistent po = (Persistent) bo;
            oid = po.getOid();
            StringDivider sd = new StringDivider(bo.getClass().getName(),".",true);
            type = sd.pre()+"::"+sd.post();
        }
        this.bo = bo;
    }

    public String writeDBString() {
        return type+"{}"+oid;
    }

    public void readDBString(String str) {
        StringDivider sd = new StringDivider(str,"{}");
        if(sd.ok()){
            type=sd.pre();
            oid=Long.parseLong(sd.post());
        }
    }
}
