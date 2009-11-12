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

import java.util.List;


public class Flex_tree_node extends Tag {

    private boolean listView;

    public void generate() {
        String label = getRequiredAttribute(LABEL);
        String type = getRequiredParentAttribute(TYPE);
        String path = getRequiredAttribute(PATH);
        boolean open = getAttribute(OPEN,false);

        listView = hasAttribute(LISTER);
        String panel = getRequiredAttributeAlternative(PANEL,LISTER);

        String icon = getAttribute(ICON, listView?"table":"edit");

        writeLine();
        write("        node = new TreeNode(\""+path+"\", \""+label+"\", "+listView+", \""+icon+"\", \""+type+"\", \""+panel+"\");");
        write("        node.defaultOpen="+open+";");

        if(getParent() instanceof Flex_tree_node) {
        write("        parent.addTemplate(node);");
        } else {
        write("        root = node;");
        }

        visitChildren(COMMAND, new Visitor(){
            public void visit(Tag visitor) {
                visitor.generate();
                write("        node.addCommand(cmd);");
            }
        },false);

        if(hasSubNodes()){
           write("        parent = node;");
        }

        visitChildren(TREE_NODE);


        if(listView){
           write("");
           write("        parent = parent.parent; // end of "+getAttribute(LISTER,""));
        }

    }

    private boolean hasSubNodes() {
        List<Tag> tags = getChildren(Flex_tree_node.class);
        return tags.size()>0;
    }



}