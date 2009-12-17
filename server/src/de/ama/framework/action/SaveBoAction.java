package de.ama.framework.action;

import de.ama.services.Environment;
import de.ama.services.PersistentService;

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

        PersistentService ps = Environment.getPersistentService();
        ps.attacheObject(data);
        commit();

        data = ps.releaseObject(data);

    }

}