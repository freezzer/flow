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
public class Tag_tree_node extends Tag {
    private boolean listView;

    protected void beginWrite() {
        String label = getAttribute(LABEL);
        String model = getParentAttribute(MODEL,"");
        String path = getRequiredAttribute(PATH);
        String panel = getAttribute(PANEL, "default");
        boolean open = getAttribute(OPEN,false);
        String lister = getAttribute(LISTER,"");
        listView = Util.isNotEmpty(lister);

        String icon = getAttribute(ICON, listView?"table":"edit");

        writeLine();
        write("      node = new TreeNode(\""+path+"\", \""+label+"\", "+listView+", \""+icon+"\", \""+model+"\", \""+(listView?lister:panel)+"\");");
        write("      node.defaultOpen="+open+";");

        if(getParent() instanceof Tag_tree_node) {
           write("      parent.addTemplate(node);");
        } else {
           write("      root = node;");
        }

        if(hasSubNodes()){
           write("      parent = node;");
        }

    }

    private boolean hasSubNodes() {
        List<Tag> tags = getChildren(Tag_tree_node.class);
        return tags.size()>0;
    }


    protected void endWrite() {
        if(listView){
           write("      parent = parent.parent;");
        }
    }


}