package de.ama.framework.action;

import de.ama.framework.data.Data;
import de.ama.services.Environment;
import de.ama.services.PersistentService;

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

        Object o = selectionModel.getSingleSelection();
        PersistentService ps = Environment.getPersistentService();
        data = ps.getObject(ps.getOidString(o));
        data = ps.releaseObject(data);

    }

}