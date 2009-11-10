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
import de.ama.util.Util;

public class Flex_menuitem extends Tag {

    @Override
    public void generate() {
        visitChildren(COMMAND,new Visitor(){
            public void visit(Tag visitor) {
                String name  =  visitor.getAttribute(NAME);
                String label =  visitor.getAttribute(LABEL, "");
                String use =    visitor.getAttribute(USE, "");
                String editor = visitor.getAttribute(EDITOR, "");
                String lister = visitor.getAttribute(LISTER, "");

                write("       <menuitem label='" + label + "' command='" + Util.saveToString(use, name) + "' "
                        + (lister.length() > 0 ? " lister='" + lister + "' " : "")
                        + (editor.length() > 0 ? " editor='" + editor + "' " : "")
                        + " />");
            }
        },false);
    }
}