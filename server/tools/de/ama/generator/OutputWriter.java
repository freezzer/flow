package de.ama.generator;


import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import de.ama.util.Util;


/**
 * Klasse die direkt vom Starter aufgerufen wird und das File-handling ausfuehrt. Hier wird auch der zentrale Objektstore
 * und das classDictionary gehalten.
 */

public class OutputWriter {
    private Tag templateRoot;
    private String inFilename;
    private String root_package;
    private File inDir;
    private String targets;
    private Map objectStore = new HashMap();
    private Map<String,String> classDictionary = new HashMap<String,String>();

    public void registerClass(String key, String className) {
//        System.out.println("OutputWriter.registerClass "+key+" "+className);

        String s = classDictionary.put(key, className);
        if(s!=null){
            throw new RuntimeException("duplicate key["+key+"] for class "+ className);
        }
    }

    public String getFullClassName(String key, Tag tag){
        String className = classDictionary.get(key);
        if(className==null){
            throw new RuntimeException("no class for  key["+key+"] registered \n look at --> <"+tag.getName()+" "+tag.getAttributesString()+" ../>");
        }
        return className;
    }

    public boolean isRegisteredClass(String type) {
        return classDictionary.containsKey(type);
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
            e.printStackTrace();
            throw new RuntimeException("Can't read inputFile " + fileName);
        }

        return new Tag(doc.getRootElement());
    }

    public OutputWriter(String inFile, String outDir, String targets) {
        this.inFilename = inFile;
        this.root_package = outDir;
        this.targets = targets;
    }

    public String getRootPackage() {
        return root_package;
    }

    protected void composeTemplates() {

        try {
            if (inDir == null) {
                inDir = new File(inFilename).getParentFile();
            }
            templateRoot = readTemplateFile(new File(inFilename).getName());
            templateRoot.setWriter(this);

        } catch (Exception e) {
            logError("Das Template " + inFilename + " konnte nicht eingelesen werden!"+ Util.getAllExceptionInfos(e));
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

    private static String knownAspects = "java,flex,data,swing,gwt";
    public void start() {

        String[] strings = targets.split(",");
        for (int i = 0; i < strings.length; i++) {
            String[] temp = strings[i].split(":");
            Tag.actual_aspect = temp[0];
            Tag.actual_aspect_dir = temp[1] ;
            if(knownAspects.indexOf(Tag.actual_aspect)<0){
                throw new IllegalArgumentException("unknown target "+Tag.actual_aspect);
            }

            System.out.println("generating for target "+Tag.actual_aspect+" to dir "+Tag.actual_aspect_dir);
            objectStore.clear();

            composeTemplates();

            ((Tag) templateRoot).execute();
        }


    }


    public String getTargets() {
        return targets;
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
}
