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

import java.util.List;

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

        String use = getAttribute(USE, "");
        if (Util.isNotEmpty(use)) return;  // reiner Platzhalter

        String name = getRequiredAttribute(NAME);
        String dir =  getDir();
        String pckg =  getPackage();

        String label = getAttribute(LABEL,"");
        String path = getAttribute(PATH,"");
        int x = getAttribute(X,0);
        int y = getAttribute(Y,0);
        int w = getAttribute(W,-1);
        int h = getAttribute(H,-1);
        String gap = getParentAttribute(GAP,"");
        boolean border = getAttribute(BORDER,false);

        initPrintWriter(dir,name+".as");

        write("/* ");
        write(getStoredObject(COMMENT));
        write("*/ ");

        writeLine();
        write("package "+pckg+" {");
        write("import de.ama.framework.gui.fields.*;");
        write("import de.ama.framework.util.*;");
        write("import de.ama.framework.gui.frames.EditPanel;");

        write("public class "+name+" "+" extends EditPanel { ");
        write("    public function "+name+"() {");
        write("        var field:EditField;");
        write("        var panel:EditPanel;");
        write("        x="+x+";  y="+y+";");
        write("        setStyle(\"borderStyle\",\""+(border?"solid":"none")+"\");");
   if(w>0)
        write("        width="+w+";");
   if(h>0)
        write("        height="+h+";");
   if(Util.isNotEmpty(gap))
        write("        gap="+gap+";");
   if(Util.isNotEmpty(label))
        write("        label=\""+label+"\";");
   if(Util.isNotEmpty(path))
        write("        path=\""+path+"\";");
        write("   ");

        collectCode(Tag_bootstrap.FORCE_IMPORT, "import "+pckg+"."+name+";");
        collectCode(Tag_bootstrap.REGISTER_PANEL, "         Factory.registerPanel(\""+name+"\", "+name+");");
    }

    protected void endWrite() {
        String use = getAttribute(USE, "");
        if (Util.isNotEmpty(use)) return;  // reiner Platzhalter

        write("   ");
        List<Tag> tags = getChildren(Tag_panel.class);
        for (int i = 0; i < tags.size(); i++) {
            Tag tag = tags.get(i);
            if(tag.isLeaf()){
                String panelName = tag.getRequiredAttribute(USE);
                String label = tag.getAttribute(LABEL,"");
                String path = tag.getAttribute(PATH,"");
                int x  = tag.getAttribute(X,-1);
                int y  = tag.getAttribute(Y,-1);
                int w  = tag.getAttribute(W,-1);
                int h  = tag.getAttribute(H,-1);
                String gap  = tag.getParentAttribute(GAP,"");
                boolean  border = tag.getAttribute(BORDER,false);
                write("        panel=Factory.createPanel(\""+panelName+"\");");
           if(Util.isNotEmpty(label))
                write("        panel.label=\""+label+"\";");
           if(x>=0)
                write("        panel.x="+x+";");
           if(y>=0)
                write("        panel.y="+y+";");
           if(w>=0)
                write("        panel.width="+w+";");
           if(h>=0)
                write("        panel.height="+h+";");
           if(Util.isNotEmpty(gap))
                write("        panel.gap="+gap+";");
           if(Util.isNotEmpty(path))
                write("        panel.path=\""+path+"\";");

                write("        panel.setStyle(\"borderStyle\",\""+(border?"solid":"none")+"\");");
                write("        addChild(panel);");
            } else {
                String panelName = tag.getRequiredAttribute(NAME);
                write("        addChild(Factory.createPanel(\""+panelName+"\"));");
            }
        }
        write("    }");
        write("}}");
        flush();
    }



}