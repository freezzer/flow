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

public class Tag_editor extends Tag {
    @Override
    protected int getIndent() {
        return 0;
    }

    @Override
    protected void beginWrite() {
        String model = getRequiredParentAttribute(MODEL);
        String editorName = getParentAttribute(NAME,model+"Editor");
        String dir =  getParentAttribute(FLEX_DIR,"");
        String pckg =  getParentAttribute(FLEX_PACKAGE,"na");
        if("na".equals(pckg)){  pckg = dir.replace('/','.'); }

        initPrintWriter(dir,editorName+".mxml");

        write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        write("<TreeEditor xmlns=\"de.ama.framework.gui.frames.*\" xmlns:mx=\"http://www.adobe.com/2006/mxml\">");
        write("   <mx:Script> <![CDATA[");
        write("     import de.ama.framework.gui.frames.*; ");
        write("     import de.ama.framework.command.*;; ");
        writeLine();

    }

    protected void mainWrite() {
        write("     override public function getPrototypeTree():TreeNode {");
        write("       var parent:TreeNode;");
        write("       var node:TreeNode;");
        write("       var root:TreeNode;");
        writeLine();
        
        // hier kommen die TreeNodes
    }


    protected void endWrite() {
        write("       return root; ");
        write("     } ");
        write("   ]]></mx:Script>");
        write("</TreeEditor>");

        writeLine();
        flush();
    }




}