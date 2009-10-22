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


package de.ama.generator.java;

import de.ama.generator.Tag;
import de.ama.util.Util;

/**
 * User: x
 * Date: 25.04.2008
 */
public class Tag_collection extends Tag {

    public int getIndent() {
        return 4;
    }

    protected void mainWrite() {
        String name = getRequiredAttribute(NAME);
        String type = getAttribute(TYPE, getChild(0).getAttribute(NAME));
        String list="java.util.List<"+type+">";

        writeLine();
        write("private "+list+" "+ name +" = new java.util.ArrayList<"+type+">();");
        write("public  "+list+" get" + Util.firstCharToUpper(name)+"() { return "+name+"; }");
        write("public  void   set" + Util.firstCharToUpper(name)+"("+list+" in) { "+ (getAttribute(MANDATORY,false)?"check(in,\""+name+"\"); ":" ") + name+"=in; }");
//        write("public  void addItemTo" + Util.firstCharToUpper(name)+"( "+type+" in) { "+name+".add(in); }");
//        write("public  "+type+" getItemFrom" + Util.firstCharToUpper(name)+"(int i) { return "+name+".get(i); }");
    }


}