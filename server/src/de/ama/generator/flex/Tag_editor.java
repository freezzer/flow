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
        String dir =  getDir();
        String pckg =  getPackage();

        collectCode(Tag_bootstrap.FORCE_IMPORT, "import "+pckg+"."+editorName+";");
        collectCode(Tag_bootstrap.REGISTER_EDITOR, "         Factory.registerEditor(\""+editorName+"\", "+editorName+");");

        initPrintWriter(dir,editorName+".as");
        write("package "+pckg+" {     ");
        write("import de.ama.framework.gui.frames.*; ");
        write("import de.ama.framework.command.*; ");
        write("import de.ama.framework.data.Data;");
        write("import de.ama.framework.util.*;");
        write("public class "+editorName+" extends TreeEditor {");
        write("     ");
        write("     override public function createData():Data {");
        write("       return Factory.createBean(\""+model+"\"); ");
        write("     } ");
        write("     ");

    }

    protected void mainWrite() {
        write("     override public function addCommands():void {");
        write("        var cmd:Command;");
                        executeChildren(Tag_command.class);
        write("     } ");
        write("");
        write("     override public function getPrototypeTree():TreeNode {");
        write("       var parent:TreeNode;");
        write("       var node:TreeNode;");
        write("       var root:TreeNode;");
        write("       var cmd:Command;");
        write("");
            executeChildren(Tag_tree_node.class);
        write("       return root; ");
        write("     } ");
    }


    protected void endWrite() {
        write("   }");
        write("}");

        writeLine();
        flush();
    }




}