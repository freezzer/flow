package de.ama.generator.java;

import de.ama.generator.Tag;

/**
 * Created by IntelliJ IDEA.
 * User: ama
 * Date: 03.11.2009
 * Time: 20:08:18
 * To change this template use File | Settings | File Templates.
 */
public class Tag_permission extends Tag {
    public static final String REGISTER_PERMISSION_SWITCH = "REGISTER_PERMISSION_SWITCH";

    public int getIndent() {
        return 0;
    }

    protected void mainWrite() {
        String name = getRequiredAttribute(NAME);
        String dir =  getDir();
        String pckg =  getPackage();
        initPrintWriter(dir,name+".java");

        write("/* ");
        write(getStoredObject(COMMENT));
        write("*/ ");
        writeLine();
        write("package "+pckg+";");
        writeLine();
        write("import de.ama.services.permission.PermissionSwitch;");
        writeLine();
        write("public class "+name+" extends de.ama.services.permission.PermissionContext { ");
        writeLine();
        write("    @Override");
        write("    protected void addSwitches(){");
        writeLine();
                       write(getCollectedCode(REGISTER_PERMISSION_SWITCH));
        write("    }");
        writeLine();
        write("}");
        flush();

        collectCode(Tag_bootstrap.BOOTSTRAP_IMPORT, "import "+pckg+"."+name+";");
        collectCode(Tag_bootstrap.REGISTER_PERMISSION, "         Environment.getPermissionService().registerPermissionContext(\""+name+"\", "+name+".class);");

    }


    protected void endWrite() {
    }

}
