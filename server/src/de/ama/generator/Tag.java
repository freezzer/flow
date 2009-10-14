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


package de.ama.generator;

import de.ama.util.Util;
import de.ama.util.XmlElement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ListIterator;
import java.util.List;
import java.util.ArrayList;

/**
 * User: x
 * Date: 23.04.2008
 */
public class Tag extends XmlElement implements Const {
    protected  static String NOT_AVAILABEL = "na";
    public static String target; 

    private OutputWriter writer;
    private PrintWriter printWriter;

    protected boolean withAttributes() {
        return true;
    }

    public String getFlavour(){
        String tmp = getClass().getPackage().getName();
        return tmp;
    }


       public Tag() {
    }

    public Tag(org.jdom.Element je) {
        readJdomElement(je);
        readRecursive(je);
    }


    public PrintWriter createPrintWriter(String _dir, String fileName) {
        try {
            File dir = null;
            if(_dir.contains(":")){
              dir= new File(_dir);
            } else {
              dir = new File(getWriter().getOutDir()+"/"+ _dir );
            }
            if (!dir.exists()) {
                dir.mkdirs();
            }

            return new PrintWriter(new FileWriter(new File(dir , fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initPrintWriter(String _dir, String fileName) {
        PrintWriter printWriter = createPrintWriter(_dir, fileName);
        setPrintWriter(printWriter);
    }


    protected String getStandardAttributesString(){
        String tmp = "";

        tmp += readStandardAttribute(NAME,"name");
        tmp += readStandardAttribute(BGCOLOR,"bgcolor");
        tmp += readStandardAttribute(X,"x");
        tmp += readStandardAttribute(Y,"y");

        return tmp;
    }

    protected String readStandardAttribute(String key){
        return readStandardAttribute(key,key); 
    }

    protected String readStandardAttribute(String key, String aName){
        if(hasAttribute(key)) return " "+aName+"='"+getAttribute(key)+"'";
        return "";
    }

    protected String readPathAttributeLaszlo(){
        String path = getAttribute(PATH,"");
        if(Util.isNotEmpty(path))   {
            if(path.startsWith("data:")){
               path=" datapath='local:classroot.data:/"+path.substring(5)+"'";
            } else if(path.startsWith("query:")){
               path=" datapath='local:classroot.query:/"+path.substring(6)+"'";
            } else {
                path=" datapath='"+path+"'";
            }
        }
        return path;
    }

    // ----------------- properties --------------------------

    boolean isVerbose() { return "true".equals(getParentAttribute("verbose","false"));   }



    protected Tag getTagImpl(String className) {
        try {
            Class clazz = (Class) Class.forName(className);
            if (clazz != null) {
                Tag i = (Tag) clazz.newInstance();
                return i;
            }
        } catch (Exception e) {
           return  null;
        }
        return null;
    }

    protected XmlElement getEmptyInstance(org.jdom.Element je) {

        String className = "de.ama.generator." + Tag.target + ".Tag_" + je.getName();
        Tag tag = getTagImpl(className);
        if(tag!=null) return tag;

        className = "de.ama.generator.Tag_" + je.getName();
        tag = getTagImpl(className);
        if(tag!=null) return tag;

        return new Tag();
    }

    public void collectCode(String key,String code){
        List fragments = (List) getStoredObject(key);
        if(fragments==null){
            fragments = new ArrayList();
            storeObject(key,fragments);
        }
        fragments.add(code);
    }

    public String getCollectedCode(String key){
        List fragments = (List) getStoredObject(key);
        if(fragments==null)  return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fragments.size(); i++) {
            String code = (String) fragments.get(i);
            sb.append(code).append("\n");
        }
        return sb.toString();
    }

    public void storeObject(String key, Object o) {
        ((Tag)getRoot()).getWriter().getObjectStore().put(key, o);
    }

    public Object getStoredObject(String key) {
        return getWriter().getObjectStore().get(key);
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public PrintWriter getPrintWriter() {
        if(printWriter!=null){
            return printWriter;
        }
        return ((Tag)getParent()).getPrintWriter();
    }

    public void prepareRecursive() {
        super.prepareRecursive();
    }

    public String getRequiredAttribute(String key) {
        if(!hasAttribute(key)) {
            throw new RuntimeException("required attribute "+key+" not present, see Tag<"+getName()+">");
        }
        return getAttribute(key);
    }

    public String getParentAttribute(String key,String def) {
        if(hasAttribute(key) || isRoot()) {
            return getAttribute(key, def);
        }
        return ((Tag)getParent()).getParentAttribute(key, def);
    }

    public String getRequiredParentAttribute(String key) {
        String s = getParentAttribute(key, "na");
        if("na".equals(s)){
            throw new RuntimeException("required parent_attribute "+key+" not present, Tag<"+getName()+">");
        }
        return s;
    }


    public String getId() {
        return getAttribute("id");
    }

    private void logError(String txt, Throwable t){
       getWriter().logError(txt, t);
    }

    private void logError(String txt){
        System.out.println("error "+txt);
       getWriter().logError(txt);
    }

    public OutputWriter getWriter() {
        if(isRoot())  return writer;
        return ((Tag)getRoot()).getWriter();
    }

    public void setWriter(OutputWriter writer) {
        this.writer = writer;
    }

    public void execute(){
        beginWrite();

        mainWrite();

        childrenWrite();

        endWrite();

    }


    protected void childrenWrite() {
        ListIterator listIterator = getChildIterator();
        while (listIterator.hasNext()) {
            Tag tag = (Tag) listIterator.next();
            tag.execute();
        }
    }

    protected void mainWrite() {
        //To change body of created methods use File | Settings | File Templates.
    }

    protected void beginWrite() {
        //To change body of created methods use File | Settings | File Templates.
    }

    protected void endWrite() {
        //To change body of created methods use File | Settings | File Templates.
    }

    protected boolean containsScript(String in){
        if(Util.isEmpty(in)) return false;
        if(in.contains(".")) return true;
        if(in.contains("*")) return true;
        if(in.contains("/")) return true;
        if(in.contains("+")) return true;
        if(in.contains("-")) return true;
        return false;
    }

    protected String scriptEnvelope(String in, String pre) {
        if(containsScript(in)){
            return "$"+Util.saveToString(pre)+"{"+in+"}";
        }
        return in;
    }

    protected void write(Object str){
        getPrintWriter().println(Util.createString(getIndent(),"  ")+str);
    }

    protected int getIndent() {
        return getLevel();
    }

    protected void writeLine(){
       getPrintWriter().println();
    }

    protected void flush(){
       getPrintWriter().flush();
    }

    protected Tag getChild(int i) {
        return (Tag) getChildren().get(i);
    }


}
