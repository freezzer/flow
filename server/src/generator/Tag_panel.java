/*
    This file is part of GNA-JORM Java-Object-Relational-Mapper.

    GNA-JORM is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    GNA-JORM is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
    
*/


package generator;

import de.ama.util.Util;

import java.io.PrintWriter;

/**
 * User: x
 * Date: 25.04.2008
 */
public class Tag_panel extends Tag {

    protected void beginWrite() {
        String spacing = getAttribute(SPACING,"5");
        boolean horizontal = getAttribute(HORIZONTAL,false);
        String title = getAttribute(TITLE,"");
        String path = readPathAttribute();
        String w = readStandardAttribute(W,"width");
        String h = readStandardAttribute(H,"height");
        String v = readStandardAttribute(VISIBLE,"visible");

        write("<view"+w+h+path+v+getStandardAttributesString()+" >");
        write(" <simplelayout spacing='"+spacing+"' axis='"+(horizontal?"x":"y")+"'/>");
        if(title.length()>0){
        write(" <text> <b>"+title+"</b> </text>");
        }
    }

    protected void endWrite() {
        write("</view>");
        flush();
    }

}