
package de.ama.services;

import de.ama.framework.action.ActionScriptAction;
import de.ama.framework.action.ActionTransporter;

/**
 * User: x
 * Date: 13.10.2008
 */
public interface ActionService {
    public static final String NAME = "ActionService";

    public ActionScriptAction execute(ActionTransporter asa);
}
