package de.ama.framework.action;

import de.ama.framework.data.Data;
import de.ama.framework.data.BoReference;
import de.ama.services.Environment;
import de.ama.services.PersistentService;
import de.ama.db.Query;

/**
 * Created by IntelliJ IDEA.
 * User: ama
 * Date: 30.04.2009
 * Time: 18:30:43
 * To change this template use File | Settings | File Templates.
 */
public class LoadBoAction extends ActionScriptAction {

    @Override
    public void execute() throws Exception {
        PersistentService ps = Environment.getPersistentService();
        Object bo;

        if (data instanceof BoReference) {
            BoReference ref = (BoReference) data;
            bo = ps.getObject(new Query(ref.getType(),"oid",Query.EQ, ref.getOid()),false);

        } else {
            BoReference boRef = (BoReference) selectionModel.getSingleSelection();
            bo = boRef.getBo();
        }

        data = ps.releaseObject(bo);

    }

}