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

public class Tag_panel extends Tag {
    @Override
    protected int getIndent() {
        return 0;
    }

    protected void beginWrite() {
        String name = getParentAttribute(NAME,"");
        String dir =  getParentAttribute(FLEX_DIR,"");
        String pckg =  getParentAttribute(FLEX_PACKAGE,"na");

        String spacing = getAttribute(SPACING,"5");
        boolean horizontal = getAttribute(HORIZONTAL,false);
        String title = getAttribute(TITLE,"");
        String path = getRequiredAttribute(PATH);
        String w = readStandardAttribute(W,"width");
        String h = readStandardAttribute(H,"height");
        String v = readStandardAttribute(VISIBLE,"visible");

        initPrintWriter(dir,name+".as");

        write("/* ");
        write(getStoredObject(COMMENT));
        write("*/ ");

        writeLine();
        write("package "+pckg+" {");
        write("import de.ama.framework.data.*;");
        write("import de.ama.framework.util.*;");

        write("public class "+name+" "+" extends EditPane { ");

        collectCode(Tag_bootstrap.FORCE_IMPORT, "import "+pckg+"."+name+";");
        collectCode(Tag_bootstrap.REGISTER_PANEL, "         Factory.registerPanel(\""+name+"\", "+name+");");
    }

    protected void endWrite() {
        writeLine();
        write("}}");
        flush();
    }



}