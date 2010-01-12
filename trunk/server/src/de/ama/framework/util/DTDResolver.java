package de.ama.framework.util;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: ama
 * Date: 23.03.2007
 * Mit dieser Klasse ist es moeglich ein DTD aus unseren resources zu laden.
 * Einem SaxParser oder Jdom Builder kann ein solcher DTDResolver uebergeben werden.
 * Beispiel:
 * JDOM:
 *       SAXBuilder builder = new SAXBuilder(false);
 *       builder.setEntityResolver(new DTDResolver());
 * SAX:
 *       XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
 *       reader.setEntityResolver(new DTDResolver());
 */             
public class DTDResolver implements EntityResolver {
    /**
     * Allow the application to resolve external entities.
     * <p/>
     * <p>The Parser will call this method before opening any external
     * entity except the top-level document entity (including the
     * external DTD subset, external entities referenced within the
     * DTD, and external entities referenced within the document
     * element): the application may request that the parser resolve
     * the entity itself, that it use an alternative URI, or that it
     * use an entirely different input source.</p>
     * <p/>
     * <p>Application writers can use this method to redirect external
     * system identifiers to secure and/or local URIs, to look up
     * public identifiers in a catalogue, or to read an entity from a
     * database or other input source (including, for example, a dialog
     * box).</p>
     * <p/>
     * <p>If the system identifier is a URL, the SAX parser must
     * resolve it fully before reporting it to the application.</p>
     *
     * @param publicId The public identifier of the external entity
     *                 being referenced, or null if none was supplied.
     * @param systemId The system identifier of the external entity
     *                 being referenced.
     * @return An InputSource object describing the new input source,
     *         or null to request that the parser open a regular
     *         URI connection to the system identifier.
     * @throws org.xml.sax.SAXException Any SAX exception, possibly
     *                                  wrapping another exception.
     * @throws java.io.IOException      A Java-specific IO exception,
     *                                  possibly the result of creating a new InputStream
     *                                  or Reader for the InputSource.
     * @see org.xml.sax.InputSource
     */
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
        InputSource ret = null;
        if(systemId.toUpperCase().endsWith(".DTD")){
            if(systemId.startsWith("de")) {
                InputStream is = ClassLoader.getSystemResourceAsStream(systemId);
                ret = new InputSource(is);
            } else {
                ret = new InputSource(systemId);
            }
        }

        if(ret==null){
            System.out.println("***************************************************************");
            System.out.println("* DTDResolver kann systemId ["+ systemId +"] nicht aufloesen ");
            System.out.println("***************************************************************");
        }

        return ret;
    }
}
