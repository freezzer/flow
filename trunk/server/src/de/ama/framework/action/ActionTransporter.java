package de.ama.framework.action;

import de.ama.framework.data.SelectionModel;
import de.ama.services.Environment;

import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.IOException;
import java.io.ObjectInput;

/**
 * Created by IntelliJ IDEA.
 * User: ama
 * Date: 02.02.2010
 * Time: 23:29:32
 * To change this template use File | Settings | File Templates.
 */
public class ActionTransporter implements java.io.Serializable, Externalizable {
    public String catalog;
    public ActionScriptAction action;



    public void writeExternal(ObjectOutput output) throws IOException {
        output.writeObject(catalog);
        output.writeObject(action);
    }

    public void readExternal(ObjectInput input) throws IOException, ClassNotFoundException {
        catalog = (String) input.readObject();

        Environment.getPersistentService().join(catalog);
        System.out.println("ActionTransporter.readExternal");

        action = (ActionScriptAction) input.readObject();
    }
}
