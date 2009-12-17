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


public class Flex_menubar extends Tag {

    public void generate() {
        String w = getAttribute(W, "100%");
        String h = getAttribute(H, "20");

        write("       import de.ama.framework.command.*;");
        write("       import mx.collections.ArrayCollection;");
        write("       var cmd:Command;");
        write("       var menu:ArrayCollection;");
        writeLine();
        visitChildren(MENU);

    }

    protected void endWrite() {
    }

}