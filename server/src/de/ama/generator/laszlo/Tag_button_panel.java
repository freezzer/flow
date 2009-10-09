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

import de.ama.util.Util;
import de.ama.generator.Tag;

/**
 * User: x
 * Date: 25.04.2008
 */
public class Tag_button_panel extends Tag {



    protected void beginWrite() {
        String model = getRequiredParentAttribute(MODEL);
        String refresh   = getAttribute("refresh");
        String edit      = getAttribute("edit");
        String delete    = getAttribute("delete");
        String close    = getAttribute("close");

        write("<view name='button_panel' width='$once{mainTabs.width-2}' height='30' bgcolor='0xa9a9a9' >");
        write(" <simplelayout axis='x'/>");

        if(Util.isNotEmpty(refresh)) {
        write("  <button onclick='"+model+"_grid.refresh()' width='30' height='30'  >");
        write("    <view resource='resources/Refresh24.gif'  x='3' y='3' />");
        write("    <tooltip>"+refresh+"</tooltip>");
        write("  </button>");
        }

        if(Util.isNotEmpty(edit)) {
        write("  <button onclick='"+model+"_grid.openEditor()' width='30' height='30' >");
        write("    <view resource='resources/Edit24.gif'  x='3' y='3' />");
        write("    <tooltip>"+edit+"</tooltip>");
        write("  </button>");
        }

        if(Util.isNotEmpty(delete)) {
        write("  <button onclick='"+model+"_grid.deleteBean()' width='30' height='30' >");
        write("    <view resource='resources/Delete24.gif'  x='3' y='3'  />");
        write("    <tooltip>"+delete+"</tooltip>");
        write("  </button>");
        }

        if(Util.isNotEmpty(close)) {
        write("  <button onclick=\"classroot.parent.destroy()\" width='30' height='30' >");
        write("    <view resource='resources/Stop24.gif'  x='3' y='3'  />");
        write("    <tooltip>"+close+"</tooltip>");
        write("  </button>");
        }

    }


    protected void endWrite() {
        write("</view>    ");
    }
}