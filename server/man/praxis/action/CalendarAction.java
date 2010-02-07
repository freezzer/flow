// manuelle

package praxis.action;

import de.ama.framework.action.ActionScriptAction;
import de.ama.framework.data.Data;
import de.ama.framework.data.DataDictionary;
import de.ama.services.Environment;
import de.ama.services.PersistentService;

import java.util.List;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 28.01.2010
 * Time: 10:15:38
 * To change this template use File | Settings | File Templates.
 */
public class CalendarAction extends ActionScriptAction {
    public int type;
    public Date date;

    @Override
    public void execute() throws Exception {

        PersistentService ps = Environment.getPersistentService();

        if(data!=null){
            ps.attacheObject(data);
            ps.commit();
        }

        if(selectionModel.getQuery()!=null){
            List objects = ps.getObjects(selectionModel.getQuery());
            data = ps.releaseObject(objects);
        }
    }
}