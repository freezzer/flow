package de.ama.generator.java;

import de.ama.generator.Tag;

/**
 * Created by IntelliJ IDEA.
 * User: ama
 * Date: 03.11.2009
 * Time: 20:08:18
 * To change this template use File | Settings | File Templates.
 */
public class Tag_bootstrap extends Tag {
    public static final String BOOTSTRAP_IMPORT = "BOOTSTRAP_IMPORT";
    public static final String REGISTER_PERMISSION = "REGISTER_PERMISSION";
    public static final String REGISTER_BEAN = "REGISTER_BEAN";

    public int getIndent() {
        return 0;
    }

    protected void mainWrite() {
        String name = getAttribute(NAME,"Bootstrap");

        String dir =  getDir();
        String pckg =  getPackage();
        initPrintWriter(dir,name+".java");

        write("/* ");
        write(getStoredObject(COMMENT));
        write("*/ ");
        writeLine();
        write("package "+pckg+";");
        writeLine();
        write("import de.ama.services.Environment;");
        write("import de.ama.services.PermissionService;");
        write(getCollectedCode(BOOTSTRAP_IMPORT));
        writeLine();
        write("public class "+name+" { ");
        writeLine();
        write("    protected void registerBeans(){");
        writeLine();
                       write(getCollectedCode(REGISTER_BEAN));
        write("    }");
        writeLine();
        write("    protected void registerPermissions(){");
        writeLine();
                       write(getCollectedCode(REGISTER_PERMISSION));
        write("    }");
        writeLine();
        write("    public void preMain() {");
        write("         registerBeans();");
        write("         registerPermissions();");
        write("         System.out.println(\"Bootstrap done OK\");");
        write("    }");
        writeLine();
        write("}");
        flush();

    }


    protected void endWrite() {
    }

}