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
import de.ama.generator.Visitor;
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
        String pckg =  getPackage();
        String icon = getAttribute(ICON, "");
        String label = getAttribute(LABEL, "");
        String use = getAttribute(USE, "");
        String editor = getAttribute(EDITOR, "");
        String lister = getAttribute(LISTER, "");
        String permitted = getAttribute(PERMITTED, "");

        String permissionContext = getParentAttribute(PERMISSION,NAME,"global");
        icon = isEmpty(icon) ? "" : ","+quote(icon);

        if (Util.isNotEmpty(name)) // import non CRUD command
        write("        import " + pckg + "." + name + ";");
        write("        cmd = new " + Util.saveToString(use, name) + "(\"" + label + "\"" + icon + ");");
        write("        cmd.permissionId = \""+permissionContext+":"+Util.saveToString(use, name)+" ("+ label+")\";");

        if (Util.isNotEmpty(editor))
        write("        cmd.setProperty(\"editor\",\"" + editor + "\");");
        if (Util.isNotEmpty(lister))
        write("        cmd.setProperty(\"lister\",\"" + lister + "\");");
        if (Util.isNotEmpty(name))
        write("        cmd.setProperty(\"name\",\"" + name + "\");");
        if (Util.isNotEmpty(permitted))
        write("        cmd.setProperty(\"permitted\",\"" + permitted + "\");");

    }


}