package de.ama.services.impl;

import de.ama.db.*;
import de.ama.services.PersistentService;
import de.ama.services.UserService;
import de.ama.util.Util;

import java.util.*;

/**
 * User: x
 * Date: 19.05.2008
 */
public class PersistentServiceImpl implements PersistentService {

    class Keepalive implements Runnable {
        boolean stop = false;

        public void run() {
            while (!stop) {

                try {
                    join(DB.get().getDefault_catalog());
                    getNextNumber("keepalive");
                    commit();
                    leave();
                    Util.sleep(1000*60); // 1 Min
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    Keepalive keepalive = new Keepalive();


    public PersistentServiceImpl() {
        start();
    }


    public void start() {
        Util.createDB();
        createSequenze(UserService.USER_ID_SEQUENZE);
        createSequenze("keepalive");
        new Thread(keepalive).start();
    }

    public void stop() {
        DB.get().disconnect();
        keepalive.stop=true;
    }

    public void join(String catalog) {
        DB.joinCatalog(catalog);
        //DB.session().setVerbose(true);
    }

    public void leave() {
        DB.leaveCatalog();
    }

    public Object getObject(String oid) {
        return DB.session().getObject(oid);
    }

    public List getObjects(Query query) {
        return DB.session().getOidIterator(query).asList();
    }

    public OidIterator getObjectsIterator(Query query) {
        return DB.session().getOidIterator(query);
    }

    public long getObjectCount(Query q) {
        return DB.session().getObjectCount(q);
    }

    public Object getObject(Query query, boolean exact) {
        OidIterator oidIterator = DB.session().getOidIterator(query);
        if (exact && oidIterator.size() > 1) {
            throw new RuntimeException("more than one Object for [" + query.toString() + "] found in DB");
        }
        if (oidIterator.size() < 1) {
            if (exact) {
                throw new RuntimeException("no Object for [" + query.toString() + "] found in DB");
            }
            return null;
        }
        return oidIterator.next();
    }

    public String makePersistent(Object o) {
        DB.session().setObject(o);
        return DB.session().getOidString(o);
    }

    public void attacheObject(Object o) {
        DB.session().attacheObject(o);
    }

    public Object releaseObject(Object o) {
        if (o instanceof Persistent) {
            Persistent persistent = (Persistent) o;
            DB.session().releaseObject(persistent);
        } else if (o instanceof Collection) {
            Collection col = (Collection) o;
            for (Iterator iterator = col.iterator(); iterator.hasNext();) {
                Object io = iterator.next();
                DB.session().releaseObject(io);
            }
        }
        return o;
    }

    public String getOidString(Object o) {
        return DB.session().getOidString(o);
    }

    public void delete(String oid) {
        DB.session().delete(oid);
    }

    public void delete(Object o) {
        if (o instanceof List) {
            List l = (List) o;
            DB.session().deleteObjects(l);
        } else {
            DB.session().delete(o);
        }
    }

    public void delete(Query q) {
        DB.session().delete(q);
    }

    public void commit() {
        DB.session().commit();
    }

    public void rollback() {
        DB.session().rollback();
    }

    public long getNextNumber(String key) {
        return DB.session().getSequenzeNext(key);
    }

    public void refresh(Object toRefresh) {
        if (toRefresh instanceof Persistent) {
            Persistent po = (Persistent) toRefresh;
            DB.session().fillObject(po, po.getOid());
        }
    }

    public void createSequenze(String picture_number) {
        join(DB.get().getDefault_catalog());
        DB.session().createSequenze(picture_number, 1);
        commit();
        leave();
    }
}
