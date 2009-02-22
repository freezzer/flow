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


package generator.flex;

import de.ama.util.Util;
import generator.Tag;

/**
 * User: x
 * Date: 25.04.2008
 */
public class Tag_field extends Tag {

    public int getIndent() {
        return 4;
    }


    protected void mainWrite() {
        String name = getRequiredAttribute(NAME);
        String type = getAttribute(TYPE,"String");
        if(!isLeaf()){
            type = getChild(0).getRequiredAttribute(NAME);
        }
        if("date".equalsIgnoreCase(type))  { type = "Date";    }
        if("number".equalsIgnoreCase(type)){ type = "Number";  }
        if("boolean".equalsIgnoreCase(type)){ type = "Boolean";  }
        writeLine();
        write("public var "+name+":"+type+";");
        if(getAttribute("label",false)){
            write("public function get label():String { return saveToString("+name+"); }");
        }
    }


}