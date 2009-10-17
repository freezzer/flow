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
public class Tag_tree_node extends Tag {
    @Override
    protected int getIndent() {
        return 0;
    }

    protected void beginWrite() {
        String label = getAttribute(LABEL);
        String prefix = getAttribute(PREFIX,label);
        String labelPath = getAttribute(LABELPATH,"");
        String path = getAttribute(PATH,"");
        String panel = getAttribute(PANEL, "default");
        boolean fixedopen = getAttribute(FIXED_OPEN,true);
        boolean listView = getAttribute(LIST_VIEW,false);

        boolean create = getAttribute(CREATE,false);
        boolean copy = getAttribute(COPY,false);
        boolean delete = getAttribute(DELETE,false);

        String icon = getAttribute(ICON,"folder");

        write("          node = new TreeNode(\""+path+"\", \""+prefix+"\", \""+labelPath+"\", "+listView+", \""+icon+"\");");
        if(create){
        write("          node.commands.addItem(new CreateNodeCommand('"+label+" anlegen'));");
        }
        if(copy){
        write("          node.commands.addItem(new CopyNodeCommand('"+label+" kopieren'));");
        }
        if(delete){
        write("          node.commands.addItem(new DeleteNodeCommand('"+label+" loeschen'));");
        }

        if(getParent() instanceof Tag_tree_node) {
           write("          parent.addPrototype(node);");
        } else {
           write("          root = node;");
        }

        if(!isLeaf()){
           write("          parent = node;");
        }

        writeLine();
    }

    protected void endWrite() {
    }


}