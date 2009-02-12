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
import de.ama.util.XmlElement;

import java.io.PrintWriter;

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
        boolean create = getAttribute(CREATE,true);
        if(!isLeaf()){
            type = getChild(0).getRequiredAttribute(NAME);
        }
        boolean str = "String".equals(type);
        if("Date".equalsIgnoreCase(type))  { type = "java.util.Date";       create = false;}
        if("Number".equalsIgnoreCase(type)){ type = "java.math.BigDecimal";  create = false; }
        writeLine();
        write("private "+type+" "+ name +";");
        if(str){
            write("public  "+type+" get" + Util.firstCharToUpper(name)+"() { return de.ama.util.Util.saveToString("+name+"); }" );
        } else {
            String lazy="";
            if(create) lazy="if("+name+"==null){ "+name+"=new "+type+"(); }";
            write("public  "+type+" get" + Util.firstCharToUpper(name)+ "() { "+lazy+" return "+name+"; }"  );
        }
        write("public  void   set" + Util.firstCharToUpper(name)+"("+type+" in) { "+ (getAttribute(MANDATORY,false)?"check(in,\""+name+"\"); ":" ") + name+"=in; }");
    }


}