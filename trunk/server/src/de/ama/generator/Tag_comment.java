


package de.ama.generator;


/**
 * User: Andreas Marochow
 * Es kann bissher nur ein gemeinsamer Kommentar fuer alle Artefakte angegeben werden.
 *
 * Beispiel:
 * <comment><![CDATA[
           generated code by dbh Generator-Framework
           this code is property of dbh Logistics IT AG
           you are not allowed to copy or redistribute this content.
    ]]></comment>

 */
public class Tag_comment extends Tag {

    public void writeFile() {
        storeObject(COMMENT, getText());
    }
}