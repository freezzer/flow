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
public class Tag_menubar extends Tag {

    protected void beginWrite() {
        String w = getAttribute(W, "100%");
        String h = getAttribute(H, "20");

//        String color = getAttribute(COLOR, "");
//        if(Util.isNotEmpty(color)) { color=" style='"+color+"colors'";  }

        writeLine();

        write(" <mx:MenuBar id='mainMenuBar' labelField='@label' width='"+w+"' height='"+h+"'  " +
              "   itemClick='components.Util.handleMenuClick(event)' top='3' left='3' right='3' >");
        write("  <mx:XMLList>");

    }

    protected void endWrite() {
        write("  </mx:XMLList>");
        write(" </mx:MenuBar>");
        flush();
    }

}