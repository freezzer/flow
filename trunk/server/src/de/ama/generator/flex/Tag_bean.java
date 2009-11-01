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

/**
 * User: x
 * Date: 25.04.2008
 */
public class Tag_bean extends Tag{


    public int getIndent() {
        return 0;
    }

    protected void beginWrite() {
        String name = getParentAttribute(NAME,"");
        String dir =  getDir();
        String pckg =  getPackage();
        
        if (getParent() instanceof Tag_bean) {
            Tag_field f = new Tag_field();
            f.setPrintWriter(getPrintWriter());
            f.addAttribute(NAME, Util.firstCharToLower(name));
            f.addAttribute(TYPE, pckg+"."+name);
            f.addAttribute(LABEL, getAttribute(LABEL));
            f.execute();
        }


        initPrintWriter(dir,name+".as");

        write("/* ");
        write(getStoredObject(COMMENT));
        write("*/ ");

        writeLine();
        write("package "+pckg+" {");
        write("import mx.collections.ArrayCollection;");
        write("import de.ama.framework.data.*;");
        write("import de.ama.framework.util.*;");

        write("public class "+name+" "+" extends Data { ");

        collectCode(Tag_bootstrap.FORCE_IMPORT, "import "+pckg+"."+name+";");
        collectCode(Tag_bootstrap.REGISTER_OBJECT, "         Factory.registerBean(\""+name+"\", "+name+");");

    }

    protected void endWrite() {
        writeLine();
        writeLine();
        write("}}");
        flush();
    }

}