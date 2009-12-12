package de.ama.framework.data;

import de.ama.util.Util;
import de.ama.util.StringDivider;
import de.ama.db.*;

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
        return Util.createClass(type.replace("::","."));
    }

    public C getBo() {
        if(bo==null && hasReference()){
            try {
                bo =  (C) DB.session().getObject(new Query(getType(),"oid",Query.EQ, getOid()));
            } catch (DBException e) {
                bo=null;
                oid=0;
            }
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
