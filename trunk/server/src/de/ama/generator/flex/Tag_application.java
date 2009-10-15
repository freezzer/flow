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
public class Tag_application extends Tag {

    protected void beginWrite() {
        String w = getAttribute(W, "100%");
        String h = getAttribute(H, "100%");

        String name = getAttribute(NAME,"Application");
        String dir =  getParentAttribute(DIR,getParentAttribute(FLEX_DIR,""));
        initPrintWriter(dir,name+".mxml");

        write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        write("<mx:Canvas xmlns:mx=\"http://www.adobe.com/2006/mxml\" xmlns:frames=\"de.ama.framework.gui.frames.*\">");
        writeLine();
    }

    @Override
    protected int getIndent() {
        return 0;
    }

    protected void endWrite() {

        write("");
        write("    <mx:Script><![CDATA[");
        write("      import de.ama.framework.gui.frames.ListPane;");
        write("      import de.ama.framework.gui.frames.TreeEditor;");
        write("      import de.ama.framework.util.Factory;");
        write("      import mx.events.MenuEvent;");
        write("");
        write("        private function handleMenuClick(evt:MenuEvent):void {\n" +
                "            var type:String = XML(evt.item).attribute(\"type\")[0];\n" +
                "            var model:String = XML(evt.item).attribute(\"model\")[0];\n" +
                "            if (type == \"lister\") {\n" +
                "                var p:ListPane = Factory.createListPane(model);\n" +
                "                p.label = model;\n" +
                "                mainTabs.addChild(p);\n" +
                "                mainTabs.selectedChild = p;\n" +
                "            }\n" +
                "            if (type == \"editor\") {\n" +
                "                var e:TreeEditor = Factory.createEditor(model);\n" +
                "                e.label = model;\n" +
                "                mainTabs.addChild(e);\n" +
                "                mainTabs.selectedChild = e;\n" +
                "            }\n" +
                "        }");
        write("");
        write("    ]]></mx:Script>");
        write("");
        write("</mx:Canvas>");
        flush();
    }

}