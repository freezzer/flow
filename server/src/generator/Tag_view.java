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

import java.io.PrintWriter;

/**
 * User: x
 * Date: 25.04.2008
 */
public class Tag_view extends Tag {

    protected void beginWrite() {
        String model = getParentAttribute(MODEL,"");
        String name = getParentAttribute(NAME,model);
        String dir =  getParentAttribute(DIR,"");
        initPrintWriter(dir,name+".lzx");

        write("<library>");
        write("<!-- ");
        write(getStoredObject(COMMENT));
        write("--> ");
        writeLine();
    }

    protected void endWrite() {
        write("</library>");
        flush();
    }

}