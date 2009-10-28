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
        String dir =  getDir();
        String pckg =  getPackage();

        String spacing = getAttribute(SPACING,"5");
        String label = getAttribute(LABEL,name);
        String path = getAttribute(PATH,"");
        int x = getAttribute(X,0);
        int y = getAttribute(Y,0);
        int w = getAttribute(W,-1);
        int h = getAttribute(H,-1);

        initPrintWriter(dir,name+".as");

        write("/* ");
        write(getStoredObject(COMMENT));
        write("*/ ");

        writeLine();
        write("package "+pckg+" {");
        write("import de.ama.framework.gui.fields.EditField;");
        write("import de.ama.framework.gui.frames.EditPanel;");

        write("public class "+name+" "+" extends EditPanel { ");
        write("    public function "+name+"() {");
        write("        var field:EditField;");
        write("        x="+x+";  y="+y+";");
   if(w>0)
        write("        width="+w+";");
   if(h>0)
        write("        height="+h+";");
        write("        label=\""+label+"\";");
        collectCode(Tag_bootstrap.FORCE_IMPORT, "import "+pckg+"."+name+";");
        collectCode(Tag_bootstrap.REGISTER_PANEL, "         Factory.registerPanel(\""+name+"\", "+name+");");
    }

    protected void endWrite() {
        write("    }");
        write("}}");
        flush();
    }



}