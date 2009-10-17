package de.ama.framework.action;

import de.ama.framework.data.Data;
import de.ama.framework.data.DataDictionary;
import de.ama.framework.data.DataTable;
import de.ama.services.Environment;
import de.ama.util.Util;

/**
 * Created by IntelliJ IDEA.
 * User: ama
 * Date: 30.04.2009
 * Time: 18:30:43
 * To change this template use File | Settings | File Templates.
 */
public class SaveBoAction extends ActionScriptAction {

    @Override
    public void execute() throws Exception {

        Environment.getPersistentService().makePersistent(data);
        commit();

    }

}