// manuelle

package praxis.action;

import de.ama.framework.action.ActionScriptAction;
import de.ama.framework.data.Query;
import de.ama.services.Environment;
import de.ama.services.PersistentService;

import java.util.List;
import java.util.Date;

import praxis.bom.CalendarEntry;

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
            data = ps.releaseObject(data);
            return;
        } else {
            List objects = ps.getObjects(new Query(CalendarEntry.class));
            data = ps.releaseObject(objects);
        }
    }
}