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
public class DataAction extends de.ama.framework.action.ActionScriptAction {

    @Override
    public void execute() throws Exception {

        if (data == null) {  // load
            Object o = Environment.getPersistentService().getObject(selectionModel.getSingleSelection().oidString);
            data = Util.createObject(DataDictionary.getDataClassForBo(o));
            mapBoToData(o, (Data) data);
        } else {           // save
            if (data instanceof DataTable) {
                saveDataTable();
            } else {
                data = storeData((Data) data);
            }
        }
    }

    private void saveDataTable() {
        DataTable table = (DataTable) data;
        for (int i = 0; i < table.size(); i++) {
            Data data = (Data) table.get(i);
            Object o = mapDataToBo(data);
            Environment.getPersistentService().makePersistent(o);
        }

        commit();
        data = null;
    }
}
