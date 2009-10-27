package de.ama.framework.action;

import de.ama.framework.data.Data;
import de.ama.framework.data.DataDictionary;
import de.ama.framework.data.DataTable;
import de.ama.framework.data.Selection;
import de.ama.services.Environment;
import de.ama.services.PersistentService;
import de.ama.util.Util;
import de.ama.db.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ama
 * Date: 30.04.2009
 * Time: 18:30:43
 * To change this template use File | Settings | File Templates.
 */
public class LoadTableAction extends ActionScriptAction {

    @Override
    public void execute() throws Exception {
        PersistentService ps = Environment.getPersistentService();
        List objects = ps.getObjects(new Query(data.getClass()));
        data = objects;

    }

}