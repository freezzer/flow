package de.ama.services.text;

import de.ama.framework.data.Condition;
import de.ama.framework.data.Query;
import de.ama.services.Environment;
import de.ama.services.TextService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 09.01.2010
 * Time: 16:41:57
 * To change this template use File | Settings | File Templates.
 */
public class TextServiceImpl implements TextService {

    public List<TextBaustein> getTextBausteine(String key) {
        Query q = new Query(TextBaustein.class, new Condition("key",Condition.LIKE,key));
        return Environment.getPersistentService().getObjects(q);
    }
}
