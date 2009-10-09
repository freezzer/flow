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
 * <button x="230" text="save" onclick="ActionService.execute('SaveBeanAction', parent.parent.action)" />
 */
public class Tag_button extends Tag {
    protected void mainWrite() {
        String label = getAttribute(LABEL,"");
        String icon = getAttribute(ICON,"");
        String action = getAttribute(ACTION);
        String x = getAttribute(X, "10");
        String type = getAttribute(TYPE, "data");

        if (Util.isNotEmpty(action)) {
            write("<button x='" + x + "' onclick=\"ActionService.execute('" + action + "', classroot.data, classroot.data)\" height='30' width='30'>");
        } else {
            String method = getRequiredAttribute(METHOD);
            write("<button x='" + x + "' onclick=\""+method+"\"  height='30' width='30'>");
        }
        if (Util.isNotEmpty(icon)) {
            write("  <view resource='resources/"+icon+"'  x='3' y='3' />");
            write("  <tooltip>"+label+"</tooltip>");
        } else {
            write(label);
        }
        write("</button>");
    }

}