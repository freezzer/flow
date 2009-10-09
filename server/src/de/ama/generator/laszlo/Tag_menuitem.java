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


package de.ama.generator.laszlo;

import de.ama.generator.Tag;
import de.ama.util.Util;

/**
 * User: x
 * Date: 25.04.2008
 */
public class Tag_menuitem extends Tag {

    protected void mainWrite() {
        String name = getRequiredAttribute(NAME);
        boolean tab = getAttribute("tab",true);
        String exec = "";

        String  editor = getAttribute("editor","");
        if(Util.isNotEmpty(editor))
           exec = "onselect=\""+editor+"_editor_helper.createTab() \"";

        String  lister = getAttribute("lister","");
        if(Util.isNotEmpty(lister))
           exec = "onselect=\""+lister+"_lister_helper.createTab() \"";

        write("<menuitem text='"+name+"' "+exec+" />");
    }


}