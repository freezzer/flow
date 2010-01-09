
package de.ama.services;

import de.ama.framework.action.ActionScriptAction;

/**
 * User: x
 * Date: 13.10.2008
 */
public interface ActionService {
    public static final String NAME = "ActionService";

    public ActionScriptAction execute(ActionScriptAction asa);
}
