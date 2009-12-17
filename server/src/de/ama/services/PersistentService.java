

package de.ama.services;

import de.ama.db.OidIterator;
import de.ama.framework.data.Query;

import java.util.List;

/**
 * User: x
 * Date: 19.05.2008
 */
public interface PersistentService {
    public static String NAME = "PersistentService";

    public void start();
    public void stop();

    public void join(String catalog);
    public void leave();

    Object getObject(String oid);
    Object getObject(Query q, boolean exact );
    List getObjects(Query q);
    public OidIterator getObjectsIterator(Query query);
    long getObjectCount(Query q);

    String makePersistent(Object o);

    void attacheObject(Object o);
    Object releaseObject(Object o);

    String getOidString(Object o);
    void delete(String oid);
    void delete(Query q);
    void delete(Object o);

    void commit();
    void rollback();

    long getNextNumber(String s);

    void refresh(Object toRefresh);

    void createSequenze(String picture_number);
}
