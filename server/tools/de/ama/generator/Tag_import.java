
package de.ama.generator;


/**
 * User: Andreas Marochow
 * Das import Tag loest einen Import auf. Es wird die Xml-Datei importiert, die im Attribute file uebergeben wird eingelesen
 * und in das aufrufende Model importiert.
 *
 * Beispiel:
 *      <import file="alias.xml"/>
        <import file="Person.xml"/>
        <import file="Vorgang.xml"/>
 */
public class Tag_import extends Tag{

    @Override
    public  void prepareElement() {
        String filename = getRequiredAttribute(FILE);
        try {
            Tag tag = getOutputWriter().readTemplateFile(filename);
            tag.prepareRecursive();
            addChild(tag);
        } catch (Exception e) {
            throw new RuntimeException("can not read import ["+ filename+"]", e);
        }
    }
}
