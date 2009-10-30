package de.ama.generator;

import de.ama.util.XmlElement;
import de.ama.util.XmlModel;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OutputWriter {
    private Tag templateRoot;
    private String inFilename;
    private String outDir;
    private File inDir;
    private String targets;
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

    public Tag readTemplateFile(String fileName) {
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

    public OutputWriter(String inFile, String outDir, String targets) {
        this.inFilename = inFile;
        this.outDir = outDir;
        this.targets = targets;
    }

    public String getOutDir() {
        return outDir;
    }

    protected void composeTemplates() {

        try {
            if (inDir == null) {
                inDir = new File(inFilename).getParentFile();
            }
            templateRoot = readTemplateFile(new File(inFilename).getName());
            templateRoot.setWriter(this);

        } catch (Exception e) {
            logError("Das Template " + inFilename + " konnte nicht eingelesen werden!", e);
        }

        if (templateRoot == null) {
            logError("Das Template " + inFilename + " konnte nicht eingelesen werden!");
        }

        // liest die imports
        templateRoot.prepareRecursive();

//        for (int i = 0; i < imports.size(); i++) {
//            Tag imp = (Tag) imports.get(i);
//            templateRoot.addChild(imp,0);
//        }
    }

    private static String knownTargets = "java,flex";
    public void start() {

        String[] strings = targets.split(",");
        for (int i = 0; i < strings.length; i++) {
            Tag.target=strings[i];
            if(knownTargets.indexOf(Tag.target)<0){
                throw new IllegalArgumentException("unknown target "+Tag.target);
            }

            System.out.println("generating for target "+Tag.target);
            objectStore.clear();

            composeTemplates();

            ((Tag) templateRoot).execute();
        }


        XmlModel m = new XmlModel(classDictionary);
        m.writeFile(new File(outDir, "repository.xml").getAbsolutePath(), true);
    }

    ///////////////////////////// Logging //////////////////////////////////////////////

    public void logInfo(String msg) {
        System.out.println("INFO : " + msg);
    }

    public void logError(String msg, Throwable e) {
        System.out.println("ERROR : " + msg);
        e.printStackTrace();
    }

    public void logError(String msg) {
        System.out.println("ERROR : " + msg);
    }

    public String getTargets() {
        return targets;
    }
}
