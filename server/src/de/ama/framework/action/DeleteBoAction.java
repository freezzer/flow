package de.ama.framework.action;

import de.ama.framework.data.Data;
import de.ama.framework.data.DataDictionary;
import de.ama.framework.data.DataTable;
import de.ama.framework.data.Selection;
import de.ama.services.Environment;
import de.ama.services.PersistentService;
import de.ama.util.Util;

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
            Selection s = (Selection) selections.get(i);
            ps.delete(getBo(s));
        }
        commit();

        data = null;
    }

}