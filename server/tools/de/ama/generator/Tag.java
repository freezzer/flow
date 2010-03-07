


package de.ama.generator;



import de.ama.util.XmlElement;
import de.ama.util.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * User: Andreas Marochow
 * Date: 23.04.2008
 *
 * Die zentrale Basisklasse aller Tags die in diesem Generator-Umfeld benutzt werden.  Erbt von XmlElement und ist somit Composite.
 * Zur Laufzeit werden alle Xml-Tags in die VM geladen und stehen zur Navigation  (ueber Visitoren) zur Verfuegung.
 *
 * Wichtige zu ueberschreibende Methoden:
 *
 * writeFile()
 * generate()
 *
 */

public class Tag extends XmlElement implements Const {
    // der Generator generiert fuer alle aspecte,
    public static String actual_aspect;
    public static String actual_aspect_dir;

    private OutputWriter writer;
    private PrintWriter printWriter;
    private File outFile;

    private boolean executed = false;       // jedes Tag wird nur einmal (fuer ein Aspekt) ausgefuehrt.
    private boolean switchedOFF = false;    // ausgeschaltet --> das ganze File (Klasse) wird manuell gepflegt

    // wir brauchen Attribute am XmlElement
    protected boolean withAttributes() {
        return true;
    }

    ////////////////////////////// Registry und Alias //////////////////////////////////////////////

    /**
     * Class-Registry zur Aufloesung von Namen im Model zu voll qualifizierten Classennamen
     * @param aspect , Ziel-Aspect (java,swing,gwt,...)
     * @param key    , key der aufgeloeste werden soll
     * @param className , voll qualifizierten Classenname
     */
    public void registerClass(String aspect, String key, String className) {
        getOutputWriter().registerClass(aspect+":"+key,className);
    }

    /**
     * Class-Registry zur Aufloesung von Namen im Model zu voll qualifizierten Classennamen
     * aspect ist der gerade aktive Aspekt .
     * @param key    , key der aufgeloeste werden soll
     * @param className , voll qualifizierten Classenname
     */
    public void registerClass(String key, String className) {
        registerClass(actual_aspect,key,className);
    }

    /**
     * Class-Registry zur Aufloesung von Namen im Model zu voll qualifizierten Classennamen
     * aspect ist der gerade aktive Aspekt .
     * @param key    , key der aufgeloeste werden soll
     * @param className , voll qualifizierten Classenname
     */
    public void registerClassAlias(String key, String className) {
        registerClass(ALIAS,key,className);
    }

    /**
     * Methode um herauszufinden ob der Name schon registriert ist.
     * @param aspect , Ziel-Aspect (java,swing,gwt,...)
     * @param key , such Schluessel
     * @return
     */
    public boolean isRegisteredClass(String aspect, String key) {
        return getOutputWriter().isRegisteredClass(aspect+":"+key) ;
    }

    /**
     * Methode um herauszufinden ob der Name schon registriert ist.
     * aspect ist der gerade aktive Aspekt .
     * @param key , such Schluessel
     * @return
     */
    public boolean isRegisteredClass(String key) {
        return isRegisteredClass(actual_aspect,key) ;
    }

    /**
     * liefert den voll qualifizierten Klassennamen fuer ein Symbol.
     * @param aspect , Ziel-Aspect (java,swing,gwt,...)
     * @param key , such Schluessel
     * @return
     */
    public String getFullClassName(String aspect, String key) {
        return getOutputWriter().getFullClassName(aspect+":"+key,this);
    }

    /**
     * liefert den voll qualifizierten Klassennamen fuer ein Symbol.
     * aspect ist der gerade aktive Aspekt .
     * @param key , such Schluessel
     * @return
     */
    public String getFullClassName(String key) {
        String ret = getOutputWriter().getFullClassName(actual_aspect +":"+key,this);
        if(ret==null){
           ret = getOutputWriter().getFullClassName(ALIAS+":"+key,this);
        }
        return ret;
    }

    ///////////////////////////// PrintWriter /////////////////////////////////////////////

    /**
     * erzeugt einen neuen PrintWriter zum schreibne einer Datei. Nicht alle Tags eroeffnen selbst einen
     * PrintWriter, viele Tags nutzen den PrintWriter eines Parent-Tags in ihrer generate() Methode.
     * @param _dir  , das directory in das geschrieben werden soll (wird automatisch angelegt).
     * @param fileName , der Name des Files
     * @return  einen neuen PrintWriter
     */
    public boolean initPrintWriter(String _dir, String fileName) {
        PrintWriter printWriter = createPrintWriter(_dir, fileName);
        if(switchedOFF) return false;

        setPrintWriter(printWriter);
        return true;
    }


    /**
     * setzt den Prinwriter fuer ein Tag explizit (um). Dadurch wird ein zuvor initialisierter Printwriter
     * ausgeblendet, und der hier uebergebene schreibt von nun an in SEINEN output.
     * @param printWriter
     */
    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    /**
     * lieefert den gerade aktiven Printwriter , dieser kann von diesem oder einem Parent-Tag kommen.
     * @return
     */
    public PrintWriter getPrintWriter() {
        if(printWriter!=null){
            return printWriter;
        }

        if(isRoot())
            return printWriter;

        return ((Tag)getParent()).getPrintWriter();
    }

    ///////////////////////// Methoden zum schreiben auf den PrintWriter ///////////////////////////////

    /**
     * schreibt eine Zeile in den output. Damit der Codeblock - Mechanissmuss funktioniert, duerfen keine
     * Zeilenumbrueche in einer Zeile vorkommen !!!
     *
     * Es wird ueber den initialisierten oder den naechsten Parent-Printwriter in den output geschrieben .
     *
     * @param str
     */
    protected void write(String str){
        ManualCodingInfo info = getManualCodingInfo();
        if(info != null && info.commentMode && !str.startsWith("//")) {
            if(str.startsWith("  ")) str = str.substring(2);
            getPrintWriter().print("//");
            getPrintWriter().println(str);
        } else {
            getPrintWriter().println(str);
        }
    }

    /**
     * diese Methode fuehrt einen neuen Codeblock ein und schliesst gleichzeitig den letzten Codeblock ab.
     * es muss ein in dem output eindeutiger Schluessel verwendet werden. Hierfuer eignet sich z.B. ein Methodenname
     * o.ae
     *
     * Ein Codeblock kann mit den Annotationen Generated und Manual an oder abgeschaltet werden.
     * @param key, der Schluessel
     */
    public  void writeCodeBlock(String key){
        writeLine();
        if(isOffBlock(key)){
            commentOn();
            write("//  @Manual(\""+key+"\")");
        } else {
            commentOff();
            write("//  @Generated(\""+key+"\")");
        }
    }

    /**
     * Es gibt pro generierter Klasse nur einen manuellen Codeblock, dieser steht meistens am Ende der Klasse.
     * Manueller Code muss zwischen diese generierten Zeilen geschrieben werden damit er den naechsten Generier-Vorgang
     * uebersteht. Um spezielle hooks aus einer Basisklasse zu ueberschreiben, die vom Generator schon implementiert werden
     * muss man die Annotationen @Manual benutzen (um generierte Methoden auszuschalten).
     * Beispiel:
     * // *****************************************************************************************
     * // [MANUAL.CODE] insert manual code here");
     * // *****************************************************************************************
     *
     *     manueller Code hier ...
     *
     * // [MANUAL.CODE.END]
     * schreibt den Codeblock fuer das manuelle codieren in den Output.
     * @see #writeCodeBlock(String)
     * @see @Manual
     * @see @Generated
     *
     */
    public  void writeManualBLock(){
        ManualCodingInfo info = getManualCodingInfo();

        commentOff();

        writeLine();
        writeLine();
        write("// *****************************************************************************************");
        write("// [MANUAL.CODE] insert manual code here");
        write("// *****************************************************************************************");
        if(info!=null && info.code !=null){
            getPrintWriter().print(info.code.toString());
        }
        write("// [MANUAL.CODE.END] ");
        writeLine();
    }

    /**
     * schreibt eine leere Zeile in den Output.
     * @see #write(String)
     * @see #flush()
     */
    protected void writeLine() {
        getPrintWriter().println();
    }

    /**
     * schreibt die Datei vollstaendig auf die Platte (quasi commit fuer den Printwriter).
     * @see #write(String)
     * @see #writeLine()
     */
    protected void flush(){
       getPrintWriter().flush();
    }

    /**
     * Utility Methode, die den uebergebenen String in doppelte Anfuehrungzeichen setzt.
     * @param in, der input
     * @return der input mit Anfuehrungzeichen umgeben
     */
    public String quote(String in){
        return "\""+in+"\"";
    }

    /**
     * Utility Methode, die den uebergebenen String in einfache Anfuehrungzeichen setzt.
     * @param in, der input
     * @return der input mit Anfuehrungzeichen umgeben
     */
    public String squote(String in){
        return "'"+in+"'";
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Dieser Template-Methode soll ueberschrieben werden in Ableitungen von Tag, um Code zu generieren.
     * Wichtig : In der generate-Methode soll kein eigener PrintWriter initialisiert werden, weil diese
     *           Methode aus anderen Tags heraus aufgerufen wird, die schon einen PrintWriter geoeffnet haben.
     */
    public void generate() {

    }

    /**
     * Diese Methode wird ueberschrieben um ein Artefakt auf die Platte zu schreiben. Es muss hierfuer ein PrintWriter
     * initialisiert (eroeffnet) werden. Hierfuer ist die Methode initPrintWriter benutzt werden.
     *
     * Zum zeilenweise Schreiben muessen die Methoden write(" code "), writeLine(), writeCodeBlock(key) und writeManualBLock()
     * benutzt werden.
     *
     * Am Ende dieser Methode muss die Methode flush() aufgerufen werden, um den Inhalt wirklich zu schreiben..
     * @see #write(String)
     * @see #writeLine()
     * @see #writeManualBLock()
     * @see #writeCodeBlock(String)
     */
    public void writeFile() {

    }

    ////////////////////////////////// Zugriff auf Tag-Attribute ///////////////////////////////////////////////

    /**
     * Zugriff auf ein Tag-Attribute das zwingend benoetigt wird. Wenn das Attribute nicht vorhanden ist bricht der
     * Generier-Vorgang mit einer entsprechenden Fehlermeldung ab.
     * @param attribute , der Name des Attributes
     * @return der Wert des Attributes
     */
    public String getRequiredAttribute(String attribute) {
        if(!hasAttribute(attribute)) {
            throwAttributeException(attribute);
        }
        return getAttribute(attribute);
    }

    /**
     * Zugriff auf ein Tag-Attribute (oder eine zweite Alternative) das zwingend benaetigt wird. Wenn das Attribute
     * oder die Alternative nicht vorhanden sind bricht der Generier-Vorgang mit einer entsprechenden Fehlermeldung ab.
     * @param attribute1 , der Name des Attributes
     * @param attribute2 , der Name der Attribute-Alternative
     * @return der Wert des Attributes
     */
    public String getRequiredAttributeAlternative(String attribute1, String attribute2) {
        if(hasAttribute(attribute1)){
            return getAttribute(attribute1);
        }
        if(hasAttribute(attribute2)){
            return getAttribute(attribute2);
        }
        throwAttributeException(attribute1+" or "+attribute2);
        return null;
    }

    /**
     * Zugriff auf ein Tag-Attribute, das auf diesem, oder einem Parent-Tag zur Verfuegung steht. Wenn das Attribute
     * nicht vorhanden ist wird der Default-Wert geliefert.
     * @param attribute , der Name des Attributes
     * @param def , der Defaultwert
     * @return der Wert des Attributes, oder der Defaultwert
     */
    public String getParentAttribute(String attribute,String def) {
        if(hasAttribute(attribute) || isRoot()) {
            return getAttribute(attribute, def);
        }
        return ((Tag)getParent()).getParentAttribute(attribute, def);
    }

    /**
     * Zugriff auf ein Tag-Attribute, das auf diesem, oder einem Parent-Tag mit einem bestimmten Tag-Namen zur Verfuegung
     * steht. Wenn das Attribute nicht vorhanden ist wird der Default-Wert geliefert.
     * @param attribute , der Name des Attributes
     * @param def, der Defaultwert
     * @param parentName, der tagname des Parents
     * @return der Wert des Attributes, oder der Defaultwert
     */
    public String getParentAttribute(String parentName, String attribute, String def) {
        if( (getName().equals(parentName) && hasAttribute(attribute)) || isRoot()) {
            return getAttribute(attribute, def);
        }
        return ((Tag)getParent()).getParentAttribute(parentName, attribute, def);
    }

    /**
     * Zugriff auf ein Parent-Tag-Attribute das zwingend benoetigt wird. Wenn das Attribute nicht vorhanden ist bricht
     * der Generier-Vorgang mit einer entsprechenden Fehlermeldung ab.
     * @param attribute , der Name des Attributes
     * @return der Wert des Attributes
     */
    public String getRequiredParentAttribute(String attribute) {
        String s = getParentAttribute(attribute, "na");
        if("na".equals(s)){
            throwAttributeException(attribute);
        }
        return s;
    }

    /**
     * @return liefert das fuer dieses Tag relevante Pacckage
     */
    public String getPackage(){
        String pckg =  getRequiredParentAttribute(PACKAGE);
        return getRootPackage() +"."+ pckg;
    }



    ////////////////////////////////////////// Navigation im Model /////////////////////////////////////////////////////

    /**
     * sucht einen Parent in der Tag-Hierarchie mit dem angegebenen Namen. Wenn das aufrufende tag den Namen enthaelt
     * wird es selbst geliefert.
     * @param name
     * @return das this, Parent-Tag oder null
     */
    public Tag getParent(String name) {
        if(getName().equals(name)){
            return this;
        }

        if(isRoot())
            return null;

        return ((Tag) getParent()).getParent(name);
    }


    /**
     * prueft ob es ein child-Tag unterhalb des aufrufenden Tags gibt.
     * @param tagName, nach dem gesucht wird
     * @param recursive , gibt an ob nur unterhalb dem aufrufenden Tag gesucht werden soll, oder auch rekursiv.
     * @return true wenn es child s gibt, sonst false
     */
    protected boolean hasChildren(String tagName, boolean recursive) {
        ListIterator listIterator = getChildIterator();
        while (listIterator.hasNext()) {
            Tag tag = (Tag) listIterator.next();
            if(tag.getName().equals(tagName)){
                return true;
            }
            if(recursive){
                return tag.hasChildren(tagName,recursive);
            }
        }
        return false;
    }

    /**
     * Liefert alle child-tags einer bestimmten Klasse unterhalb dieses Tags.
     * @param c, die Tag-Klasse nach der gesucht wird.
     * @return eine Liste aller childs der geforderten Klasse
     */
    protected List<Tag> getChildren(Class c) {
        List<Tag> ret = new ArrayList<Tag>();
        ListIterator listIterator = getChildIterator();
        while (listIterator.hasNext()) {
            Object tag = listIterator.next();
            if(c == tag.getClass()){
                ret.add((Tag) tag);
            }
        }
        return ret;
    }

    /**
     * Ueber diese Methode laesst sich ein Visitor starten. Er besucht alle childs unterhalb dieses Knotens und fuehrt
     * seine Methode visit(Tag tag) aus.
     * @param tagName, es werden nur Knoten mit dem entsprechenden tagNamen besucht.
     * @param visitor, der Visitor der die childs besucht.
     * @param recursive, gibt an ob nur unterhalb dieses Knotens oder weiter im Baum gesucht werden soll.
     * @see Visitor
     */
    protected void visitChildren(String tagName, Visitor visitor, boolean recursive) {
        List<Tag> tags = getChildren();
        for (int i = 0; i < tags.size(); i++) {
            Tag tag = tags.get(i);
            if(tag.getName().equals(tagName)){
                visitor.visit(tag);
            }

            if(recursive){
                tag.visitChildren(tagName, visitor, recursive);
            }
        }
    }


    /**
     * Es werden alle childs von der Wurzel des Modells aus rekursiv besucht.
     * @param tagName, es werden nur Knoten mit dem entsprechenden tagNamen besucht.
     * @param visitor, der Visitor der die childs besucht.
     * @see Visitor
     */
    protected void visitAllChildren(String tagName, Visitor visitor) {
        Tag root = (Tag) getRoot();
        root.visitChildren(tagName,visitor,true);
    }

    /**
     * Es werden alle childs unterhalb dieses Knotens besucht. Es wird der Standard VisitorGenerate benutzt, der wiederum
     * die generate Methode aufruft.
     *
     * @param tagName, es werden nur Knoten mit dem entsprechenden tagNamen besucht.
     *
     * @see #generate()
     * @see VisitorGenerate
     * @see Visitor
     */
    protected void visitChildren(String tagName) {
        visitChildren(tagName,new VisitorGenerate(),false);

    }

    /**
     * Es werden alle parents von diesem Knoten des Modells aus rekursiv besucht.
     * @param tagName, es werden nur Knoten mit dem entsprechenden tagNamen besucht.
     * @param visitor, der Visitor der die childs besucht.
     * @see Visitor
     */
    protected void visitParent(String tagName, Visitor visitor) {
        if(getName().equals(tagName)) {
            visitor.visit(this);
        }

        if(isRoot()) return;

        ((Tag)getParent()).visitParent(tagName, visitor);
    }


    /**
     * Zugrif auf einen child-Knoten, es ist zuvor zu pruefen ob es einen Knoten zu diesem Index gibt.
     * @param i, der child index.
     * @return
     */
    protected Tag getChild(int i) {
        return (Tag) getChildren().get(i);
    }




    //////////////////////////////////////// Util ////////////////////////////////////////

    /**
     * Es ist moeglich Werte zentral abzulegen, um sie zu einem spaeteren Zeitpunkt ueber den hier
     * verwendeten Schluessel wieder abzuholen.
     *
     * Der zentrale Speicher wird fuer jeden vom Generator durchlaufenen Aspekt geloescht. Man kann also keine
     * Werte von einem Aspekt zum anderen hinueberreten.
     *
     * @param key
     * @param value
     * @see #getStoredString(String)
     * @see #getStoredObject(String)
     */
    public void storeObject(String key, Object value) {
        ((Tag)getRoot()).getOutputWriter().getObjectStore().put(key, value);
    }

    /**
     * Einen zuvor gespeicherten Wert wieder aus de zentralen Speicher holen.
     * @param key
     * @return der Wert als Object
     */
    public Object getStoredObject(String key) {
        return getOutputWriter().getObjectStore().get(key);
    }

    /**
     * Einen zuvor gespeicherten Wert wieder aus de zentralen Speicher holen. (als String gecastet)
     * @param key
     * @return der Wert als String
     */
    public String getStoredString(String key) {
        return (String) getOutputWriter().getObjectStore().get(key);
    }

    /**
     * prueft ob der uebergeben type (als String) ein primitve im Sinne Javas ist
     * @param type
     * @return
     */
    protected boolean isTypePrimitive(String type){
       return "string.String.number.Number.int.Integer.boolean.Boolean.Text.text.Date.date".indexOf(type)>=0;
    }

    /**
     * prueft ob der uebergebene String leer ist
     * @param in
     * @return
     */
    public boolean isEmpty(String  in){
        return Util.isEmpty(in);
    }










    ////////////////////////////////////// Interne Methoden /////////////////////////////////////////////////////////

    public Tag() {    }

    public Tag(org.jdom.Element je) {
        readJdomElement(je);
        readRecursive(je);
    }

 
    public final void execute(){
        if(!executed) {
            executed = true;


            writeFile();

            ListIterator listIterator = getChildIterator();
            while (listIterator.hasNext()) {
                Tag tag = (Tag) listIterator.next();
                tag.execute();
            }

        }
    }

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

        String className = "de.ama.generator." + actual_aspect + "."+Util.firstCharToUpper(actual_aspect) +"_"+ je.getName();
        Tag tag = getTagImpl(className);
        if(tag!=null) return tag;

        className = "de.ama.generator.Tag_" + je.getName();
        tag = getTagImpl(className);
        if(tag!=null) return tag;

        Tag ret = new Tag();
        ret.setName(je.getName());
        return ret;
    }

    /**
     * @return liefert den fuer dieses Tag relevanten Verzeichniss-Pfad im temporaeren generated Verzeichniss
     */
    protected String getDir(){
        return actual_aspect_dir + "/" + getPathDir();
    }

    protected OutputWriter getOutputWriter() {
        if(isRoot())  return writer;
        return ((Tag)getRoot()).getOutputWriter();
    }

    protected void setWriter(OutputWriter writer) {
        this.writer = writer;
    }


    private String throwAttributeException(String key) {
        throw new RuntimeException("required attribute *["+key+"]* not present in:\n   look at --> <"+getName()+" "+getAttributesString()+" ../>");
    }


    private PrintWriter createPrintWriter(String _dir, String fileName) {
        try {
            File dir = new File(".",_dir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File inFile = new File(_dir,fileName);
            if(inFile.exists()){
                parseGeneratedFile(inFile);
            }

            PrintWriter ret = null;
            if(!switchedOFF) {
                outFile = new File(_dir,fileName);
                ret = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
                if(!fileName.endsWith("xml"))  {
                    ret.println("// @Generated("+_dir+"/"+fileName+")");
                }
            }


            return ret;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private String getRootPackage() {
        return getOutputWriter().getRootPackage();
    }

    private String getPathDir(){
        String dir = getParentAttribute(DIR,"");
        if(Util.isEmpty(dir)){
            String pckg =  getRequiredParentAttribute(PACKAGE);
            dir = pckg.replace(".","/");
        }

        return getRootPackage() + "/" + dir ;
    }


    ///////////////////////////// manual coding ///////////////////////////////////////////////////////////////


    private ManualCodingInfo manualCodingInfo = null;

    /**
     * Klasse zum halten von manuellem Code, damit der Generator diesen in das neue File retten kann.
     */
    private class ManualCodingInfo {
        private StringBuilder code = new StringBuilder("");             // manueller code wird hier aufbewahrt
        private List<String> offBlockLines = new ArrayList<String>();   // mit @Manual( .. ) markierte Zeilen
        private boolean commentMode = false;                            // Schaltet write Methode in CommentMode

    }

    /**
     * lazy getter fuer diesen Knoten.
     * @return
     */
    private ManualCodingInfo getManualCodingInfo(){
       if(manualCodingInfo != null || isRoot()) {
           return manualCodingInfo;
       }

       return ((Tag)getParent()).getManualCodingInfo();
    }


    /**
     * Analyse eines bereits generierten Files , um z.B. herauszufinden ob es manuellen Code/Text gibt, der gerettet
     * werden muss.
     * @param file
     */
    private void parseGeneratedFile(File file){
        boolean record = false;
        manualCodingInfo = new ManualCodingInfo();

        try {
            Scanner scanner = new Scanner(file, "UTF-8");
            boolean endReached = false;
            int count =0;
            while ( scanner.hasNextLine() && !endReached){
                count ++;

                String line = scanner.nextLine();
                if(line.indexOf("@Manual")>=0 && count == 1){  switchedOFF = true; return;  }

                if( line.indexOf("@Manual")>0 ){
                    getManualCodingInfo().offBlockLines.add(line);
                }

                if(line.indexOf("[MANUAL.CODE]")>=0)        {  record = true; continue;  }

                if(line.indexOf("[MANUAL.CODE.END]")>=0)    {  record = false; break;   }

                if(record){
                    if(manualCodingInfo.code.length()==0 && line.startsWith("// *******")){
                        continue;
                    }
                    manualCodingInfo.code.append(line).append("\n");
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * prueft ob die uebergebene Methode mit der Annotation @Manual( ) ausgeschaltet wurde.
     * @param method, der Methodenname
     * @return true wenn vom User abgeschaltet
     */
    private boolean isOffBlock(String method){
        ManualCodingInfo info = getManualCodingInfo();
        if(info == null) return false;
        
        for (int i = 0; i < info.offBlockLines.size(); i++) {
            String line = info.offBlockLines.get(i);
            if(line.indexOf(method)>=0) return true;
        }
        return false;
    }

    /**
     * schaltet den Comment-Mode ein. Es werden alle write(String str) als Kommentar geschrieben.
     */
    private void commentOn(){
        ManualCodingInfo info = getManualCodingInfo();
        if(info==null) return;

        info.commentMode = true;
    }

    /**
     * schaltet den Comment-Mode aus. Es werden alle write(String str) direkt geschrieben.
     */
    private void commentOff(){
        ManualCodingInfo info = getManualCodingInfo();
        if(info==null) return;

        info.commentMode = false;
    }


}
