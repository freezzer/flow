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

/**
 * User: x
 * Date: 25.04.2008
 */
public class Tag_command extends Tag {

    protected void beginWrite() {

        String name = getRequiredAttribute(NAME);
        String dir = getParentAttribute(DIR, "");
        dir = getParentAttribute(FLEX_DIR, dir);
        String pckg = getParentAttribute(FLEX_PACKAGE, "");
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
        write("public class "+name+" "+" extends Command { ");
        writeLine();
        write("  public function "+name+"(label:String=\""+label+"\", icon:String=\""+icon+"\") {");
        write("       super(label,icon); ");
        write("  } ");
        
        collectCode(Tag_bootstrap.FORCE_IMPORT, "import "+pckg+"."+name+";");
        collectCode(Tag_bootstrap.REGISTER_COMMAND, "         Factory.registerCommand(\""+name+"\", "+name+");");

    }

    protected void endWrite() {
        writeLine();
        write("}}");
        flush();

    }

}