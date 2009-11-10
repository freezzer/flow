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


public class Flex_menubar extends Tag {

    public void generate() {
        String w = getAttribute(W, "100%");
        String h = getAttribute(H, "20");

        writeLine();

        write(" <mx:MenuBar id='mainMenuBar' labelField='@label' width='"+w+"' height='"+h+"'" +
        "\n        itemClick='handleMenuClick(event)' " +
        "\n        top='3' left='3' right='3' >");
        write("  <mx:XMLList>");

        visitChildren(MENU);

        write("  </mx:XMLList>");
        write(" </mx:MenuBar>");


    }

    protected void endWrite() {
    }

}