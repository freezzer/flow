
package de.ama.services;

import de.ama.framework.action.ActionScriptAction;

/**
 * User: x
 * Date: 13.10.2008
 */
public interface ActionService {
    public static String NAME = "ActionService";

    public ActionScriptAction execute(ActionScriptAction asa);
}
