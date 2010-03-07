package de.ama.generator;


/**
 * User: Andreas Marochow
 * Mit diesem Tag koennnen Klassen im Model bekannt gemacht werden, die nicht (oder noch nicht ) vom Generator
 * generiert (und somit registriert) worden sind.
 *
 * Beispiel:
 *  <alias type="CopyBoCommand" class="de.dbh.framework.action.client.CopyBoCommand"/>
 *  <alias type="ButtonPanel" class="de.dbh.framework.gui.pane.ButtonPanel"/>
 */
public class Tag_alias extends Tag {

    public void writeFile() {
        String className = getRequiredAttribute(CLASS);
        String type = getRequiredAttribute(TYPE);
        registerClass(type,className);
    }
}