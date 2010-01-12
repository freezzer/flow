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


package de.ama.generator.flex;

import de.ama.generator.Tag;
import de.ama.util.Util;



public class Flex_command extends Tag {

    @Override
    public void writeFile() {

        String name = getAttribute(NAME);
        if (Util.isNotEmpty(name)) {
            String dir =  getDir();
            String pckg =  getPackage();
            String icon = getAttribute(ICON, "");
            String label = getAttribute(LABEL, "");

            initPrintWriter(dir, name + ".as");
            write("/* ");
            write(getStoredObject(COMMENT));
            write("*/ ");
            writeLine();
            write("package " + pckg + " {");
            write("import de.ama.framework.util.*;");
            write("import de.ama.framework.command.*;");
            write("import de.ama.framework.data.*;");
            write("public class " + name + " " + " extends Command { ");
            writeLine();
            write("    public function " + name + "(label:String=\"" + label + "\", icon:String=\"" + icon + "\") {");
            write("       super(label,icon); ");
            write("    } ");
            write("      ");
            write("    override protected function execute():void {");
            write("      ");
            write("    } ");
            write("    ");
            write("}}");
            flush();

        }


    }

    public void generate() {

        String name = getAttribute(NAME);
        String use = getAttribute(USE);
        String pckg =  getPackage();
        String icon = getAttribute(ICON, "");
        String label = getAttribute(LABEL, "");
        String editor = getAttribute(EDITOR, "");
        String lister = getAttribute(LISTER, "");
        String permitted = getAttribute(PERMITTED, "");
        String eventName = getAttribute(EVENT, "");
        String doubleClickEnabled = getAttribute(DEFAULT, "false");

        String permissionContext = getParentAttribute(PERMISSION,NAME,"global");
        icon = isEmpty(icon) ? "" : ","+quote(icon);

        if (!isEmpty(name)) // import non CRUD command
        write("        import " + pckg + "." + name + ";");

        write("        cmd = new " + getRequiredAttributeAlternative(USE,NAME) + "(\"" + label + "\"" + icon + ");");
        write("        cmd.permissionId = \""+permissionContext+":"+getRequiredAttributeAlternative(USE,NAME)+" ("+ label+")\";");

        if (!isEmpty(editor))
        write("        cmd.setProperty(\"editor\"," + quote(editor) + ");");
        if (!isEmpty(lister))
        write("        cmd.setProperty(\"lister\"," + quote(lister) + ");");
        if (!isEmpty(eventName))
        write("        cmd.setProperty(\"eventName\"," + quote(eventName) + ");");
        if (!isEmpty(name))
        write("        cmd.setProperty(\"name\"," + quote(name) + ");");
        if ("true".equals(permitted))
        write("        cmd.setProperty("+quote(PERMITTED)+",\"true\");");
        if ("true".equals(doubleClickEnabled))
        write("        cmd.setProperty("+quote(DEFAULT)+",\"true\");");

    }


}