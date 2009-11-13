package de.ama.generator;
/*
    This file is part of flow xml-model based app-generator using java and flex .

    flow is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    flow is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.

*/

import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import de.ama.framework.util.DTDResolver;


public class OutputWriter {
    private Tag templateRoot;
    private String inFilename;
    private String root_package;
    private File inDir;
    private String targets;
    private Map objectStore = new HashMap();

    public Map getObjectStore() {
        return objectStore;
    }

    public void setObjectStore(Map objectStore) {
        this.objectStore = objectStore;
    }

    public Tag readTemplateFile(String fileName) {
        SAXBuilder builder = new SAXBuilder(false);
        builder.setEntityResolver(new DTDResolver());
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
