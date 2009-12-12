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


public class Flex_lookup extends Tag {

    public void generate() {
        String label = getRequiredAttribute(LABEL);
        String type = getRequiredAttribute(TYPE);
        String path = getRequiredAttribute(PATH);
        String guirep = getRequiredAttribute(GUIREP);
        String editor = getAttribute(EDITOR, "");
        String lw = getParentAttribute(LABELWIDTH, "");
        int x = getAttribute(X, -1);
        int y = getAttribute(Y, -1);
        int w = getAttribute(W, -1);

        String xylw = "";
        if (x >= 0 || y >= 0) {
            xylw = " ," + x + "," + y;
            if (!isEmpty(lw)) {
                xylw += "," + lw;
            }
        }
        String width = "";
        if (w >= 0) {
            width = " ," + w;
        }

        write(" ");
        write("        field = insertProxyField(" + quote(type) + "," + quote(guirep) + "," + quote(label) + "," + quote(path) + xylw + width + ");");
        if (!isEmpty(editor)) {
            write("        ProxyField(field).editor = " + quote(editor) + ";");
        }
        if (hasChildren(LISTER,false)) {
            visitChildren(LISTER);
            write("        ProxyField(field).setListPanel(lister);");
        }

    }

}