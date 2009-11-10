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

/**
 * User: x
 * Date: 25.04.2008
 */

public class Flex_tree_editor extends Tag {
 
    public void writeFile() {
        String label = getRequiredAttribute(LABEL);
        String type = getRequiredParentAttribute(TYPE);
        String editorName = getParentAttribute(NAME,type+"Editor");
        String dir =  getDir();
        String pckg =  getPackage();
        initPrintWriter(dir,editorName+".as");

        write("package "+pckg+" {     ");
        write("import de.ama.framework.gui.frames.*; ");
        write("import de.ama.framework.command.*; ");
        write("import de.ama.framework.data.Data;");
        write("import de.ama.services.Factory;");
        write("public class "+editorName+" extends TreeEditor {");
        write("     ");
        write("     override public function createData():Data {");
        write("        return Factory.createBean(\""+type+"\"); ");
        write("     } ");
        write("     ");
        write("     override public function addCommands():void {");
        write("        label = \""+label+"\"");
                        visitParent(PERMISSION, new Visitor(){
                            public void visit(Tag visitor) {
                                write("        permissionContext=\""+visitor.getRequiredAttribute(NAME)+"\";     ");
                            }
                        });
        write("");
        write("        var cmd:Command;");
                        visitChildren(COMMAND, new Visitor(){
                            public void visit(Tag visitor) {
                                visitor.generate();
                                write("        addCommand(cmd);");
                            }
                        },false);
        write("     } ");
        write("");
        write("     override public function getPrototypeTree():TreeNode {");
        write("        var parent:TreeNode;");
        write("        var node:TreeNode;");
        write("        var root:TreeNode;");
        write("        var cmd:Command;");
        write("");
                        visitChildren(TREE_NODE);
        write("        return root; ");
        write("     } ");
        write("   }");
        write("}");
        writeLine();
        flush();
    }

}