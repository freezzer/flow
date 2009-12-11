package de.ama.framework.action;

import de.ama.framework.data.Data;
import de.ama.framework.data.BoReference;
import de.ama.services.Environment;
import de.ama.services.PersistentService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ama
 * Date: 30.04.2009
 * Time: 18:30:43
 * To change this template use File | Settings | File Templates.
 */
public class DeleteBoAction extends ActionScriptAction {

    @Override
    public void execute() throws Exception {
        PersistentService ps = Environment.getPersistentService();
        List selections = selectionModel.getSelections();
        for (int i = 0; i < selections.size(); i++) {
            Object o =  selections.get(i);
            if (o instanceof BoReference) {
                BoReference boReference = (BoReference) o;
                o= boReference.getBo();
            }
            ps.delete(o);
        }
        commit();

        data = null;
    }

}