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

import java.util.List;

/**
 * User: x
 * Date: 25.04.2008
 */
public class Tag_tree_node extends Tag {
    protected void beginWrite() {
        String label = getAttribute(LABEL);
        String type = getAttribute(TYPE,"");
        String path = getRequiredAttribute(PATH);
        String panel = getAttribute(PANEL, "default");
        boolean fixedopen = getAttribute(FIXED_OPEN,true);
        boolean listView = getAttribute(LIST_VIEW,false);

        String icon = getAttribute(ICON, listView?"table":"edit");

        writeLine();
        write("      node = new TreeNode(\""+path+"\", \""+label+"\", "+listView+", \""+icon+"\", \""+type+"\", \""+panel+"\");");

        if(getParent() instanceof Tag_tree_node) {
           write("      parent.addTemplate(node);");
        } else {
           write("      root = node;");
        }

        if(!isLeafNode()){
           write("      parent = node;");
        }

    }

    private boolean isLeafNode() {
        List children = getChildren();
        for (int i = 0; i < children.size(); i++) {
            Object o =  children.get(i);
            if (o instanceof Tag_tree_node) {
                return false;
            }
        }

        return true;
    }


    protected void endWrite() {
        if(getAttribute(LIST_VIEW,false)){
           write("      parent = parent.parent;");
        }
    }


}