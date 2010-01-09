package de.ama.services.text;

import de.ama.framework.action.ActionScriptAction;
import de.ama.services.Environment;

public class GetTextAction extends ActionScriptAction {
    public String key;

    @Override
    public void execute() throws Exception {
        data = Environment.getTextService().getTextBausteine(key);
    }
}
