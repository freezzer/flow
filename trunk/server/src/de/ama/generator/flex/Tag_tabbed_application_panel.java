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
public class Tag_tabbed_application_panel extends Tag {

    protected void beginWrite() {
        String w = getAttribute(W, "100%");
        String h = getAttribute(H, "100%");

        String name = getAttribute(NAME,"ContentPane");
        String dir =  getParentAttribute(DIR,getParentAttribute(FLEX_DIR,""));
        initPrintWriter(dir,name+".mxml");

        write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        write("<frames:TabbedApplicationPanel " +
                "\nxmlns:mx=\"http://www.adobe.com/2006/mxml\" " +
                "\nxmlns:frames=\"de.ama.framework.gui.frames.*\"" +
                "\nimplements=\"de.ama.framework.gui.frames.ApplicationPanel\">");
        writeLine();
    }

    @Override
    protected int getIndent() {
        return 0;
    }

    protected void endWrite() {

        write("");
        write("</frames:TabbedApplicationPanel>");
        flush();
    }

}