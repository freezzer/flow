package generator;

import de.ama.util.XmlElement;
import de.ama.util.XmlModel;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ama
 * Date: 28.03.2006
 * Time: 23:28:46
 * To change this template use File | Settings | File Templates.
 */

public class OutputWriter {
    private Tag templateRoot;
    private String inFilename;
    private String outDir;
    private File inDir;
    private List imports = new ArrayList();
    private String flawour;
    private Map objectStore = new HashMap();
    private XmlElement classDictionary = new XmlElement("dictionary", "");

    public void registerClass(String name, String className) {
        XmlElement node = new XmlElement("element", "");
        node.addChild(new XmlElement("name", name));
        node.addChild(new XmlElement("class", className));
        classDictionary.addChild(node);
    }


    public Map getObjectStore() {
        return objectStore;
    }

    public void setObjectStore(Map objectStore) {
        this.objectStore = objectStore;
    }

    public void readImport(String filename) {
        Tag tag = readTemplate(filename);
        tag.prepareRecursive();
        imports.add(tag);
    }


    public OutputWriter(String inFile, String outDir, String flawour) {
        this.inFilename = inFile;
        this.outDir = outDir;
        this.flawour = flawour;
    }

    public String getOutDir() {
        return outDir;
    }

    protected void readTemplate() {

        try {
            if (inDir == null) {
                inDir = new File(inFilename).getParentFile();
            }
            templateRoot = readTemplate(new File(inFilename).getName());
            templateRoot.setWriter(this);

        } catch (Exception e) {
            logError("Das Template " + inFilename + " konnte nicht eingelesen werden!", e);
        }

        if (templateRoot == null) {
            logError("Das Template " + inFilename + " konnte nicht eingelesen werden!");
        }

        templateRoot.prepareRecursive();
        for (int i = 0; i < imports.size(); i++) {
            Tag imp = (Tag) imports.get(i);
            templateRoot.addChild(imp);
        }
    }

    public Tag readTemplate(String fileName) {
        SAXBuilder builder = new SAXBuilder(false);
//        builder.setEntityResolver(new DTDResolver());
        File in = new File(inDir, fileName);
        if (!in.exists()) {
            throw new RuntimeException("Can't find inputFile " + fileName);
        }

        Document doc = null;
        try {
            doc = builder.build(in);
        } catch (Exception e) {
            throw new RuntimeException("Can't read inputFile " + fileName);
        }

        return new Tag(doc.getRootElement());
    }


    public void start() {
        objectStore.clear();
        readTemplate();
        ((Tag) templateRoot).execute();

        registerClass("QueryBeansAction", "de.ama.app.action.QueryBeansAction");
        registerClass("DeleteBeanAction", "de.ama.app.action.DeleteBeanAction");

        XmlModel m = new XmlModel(classDictionary);
        m.writeFile(new File(outDir, "repository.xml").getAbsolutePath(), true);
    }

    ///////////////////////////// Logging //////////////////////////////////////////////

    public void logInfo(String msg) {
        System.out.println("INFO : " + msg);
    }

    public void logError(String msg, Throwable e) {
        e.printStackTrace();
    }

    public void logError(String msg) {
        System.out.println("ERROR : " + msg);
    }

}
