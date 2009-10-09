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

/**
 * User: x
 * Date: 23.04.2008
 */
public class Tag extends XmlElement implements Const {
//    private    static Map    ctorMap = new HashMap();
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


//    private void registerConstructor(String id, Class clazz) {
//        ctorMap.put(id, clazz);
//    }
       public Tag() {
    }

    public Tag(org.jdom.Element je) {
//        initConstructorTable();
        readJdomElement(je);
        readRecursive(je);
    }

//    private void initConstructorTable() {
//        if (ctorMap.size() == 0) {
//            registerConstructor("java.bean",       generator.java.Tag_bean.class);
//            registerConstructor("java.field",      generator.java.Tag_field.class);
//            registerConstructor("java.collection", generator.java.Tag_collection.class);
//
//            registerConstructor("flex.field",      generator.flex.Tag_field.class);
//            registerConstructor("flex.collection", generator.flex.Tag_collection.class);
//            registerConstructor("flex.bean",       generator.flex.Tag_bean.class);
//
//
//
//            registerConstructor("action", Tag_action.class);
//            registerConstructor("application", Tag_application.class);
//            registerConstructor("tabpanel", Tag_tabpanel.class);
//            registerConstructor("comment", Tag_comment.class);
//            registerConstructor("editor", Tag_editor.class);
//            registerConstructor("grid", Tag_grid.class);
//            registerConstructor("column", Tag_column.class);
//            registerConstructor("view", Tag_view.class);
//            registerConstructor("define", Tag.class);
//            registerConstructor("lister", Tag_lister.class);
//            registerConstructor("menu", Tag_menu.class);
//            registerConstructor("menuitem", Tag_menuitem.class);
//            registerConstructor("menubar", Tag_menubar.class);
//            registerConstructor("panel", Tag_panel.class);
//            registerConstructor("tree_node", Tag_tree_node.class);
//            registerConstructor("tree_panel", Tag_tree_panel.class);
//            registerConstructor("button", Tag_button.class);
//            registerConstructor("button_panel", Tag_button_panel.class);
//            registerConstructor("input", Tag_input.class);
//            registerConstructor("execute", Tag_execute.class);
//            registerConstructor("model", Tag.class);
//            registerConstructor("import", Tag_import.class);
//            registerConstructor("file", Tag_file.class);
//        }
//    }

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

    protected String readPathAttribute(){
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
        System.out.println("Tag.execute "+ getClass().getName()+" "+getText());
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
