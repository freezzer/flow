package de.ama.services.impl;

import de.ama.db.DB;
import de.ama.db.OidIterator;
import de.ama.db.Persistent;
import de.ama.db.Query;
import de.ama.framework.data.Condition;
import de.ama.services.PersistentService;
import de.ama.services.UserService;
import de.ama.util.Util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
                    Util.sleep(1000 * 60); // 1 Min
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
        keepalive.stop = true;
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

    public List getObjects(de.ama.framework.data.Query query) {
        return DB.session().getOidIterator(toJormQuery(query)).asList();
    }


    public OidIterator getObjectsIterator(de.ama.framework.data.Query query) {
        return DB.session().getOidIterator(toJormQuery(query));
    }

    public long getObjectCount(de.ama.framework.data.Query q) {
        return DB.session().getObjectCount(toJormQuery(q));
    }

    public Object getObject(de.ama.framework.data.Query query, boolean exact) {
        OidIterator oidIterator = DB.session().getOidIterator(toJormQuery(query));
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

    public void delete(de.ama.framework.data.Query q) {
        DB.session().delete(toJormQuery(q));
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

    public void createSequenze(String prefix) {
        join(DB.get().getDefault_catalog());
        DB.session().createSequenze(prefix, 1);
        commit();
        leave();
    }

    private Query toJormQuery(de.ama.framework.data.Query boQuery) {
        Query ret = new Query(boQuery.getTarget());
        ret = ret.limit(boQuery.getLimit());
        ret = ret.orderByColumn(boQuery.getOrderColumn());
        if (boQuery.isNegated()) {
            ret = ret.negate();
        }
        return ret.and(toJormQuery(boQuery.getTarget(), boQuery.getCondition()));
    }

    private Query toJormQuery(Class target, Condition cond) {
        Query ret = new Query(target);
        if (cond != null) {
            if (cond.getChildren() != null) {
                Iterator it = cond.getChildren().iterator();
                while (it.hasNext()) {
                    Condition c = (Condition) it.next();
                    if (c.getConcatOperator() == c.AND) {
                        ret.and(toJormQuery(target, c));
                    }
                    if (c.getConcatOperator() == c.OR) {
                        ret.or(toJormQuery(target, c));
                    }
                }
            } else if(cond.getValue()!=null){
                ret = new Query(target, cond.getPath(), cond.getOp(), cond.getValue());
            }
        }
        return ret;
    }

}
