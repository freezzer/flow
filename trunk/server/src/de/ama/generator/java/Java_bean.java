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
import de.ama.generator.Visitor;
import de.ama.util.Util;

/**
 * User: x
 * Date: 25.04.2008
 */
public class Java_bean extends Tag{
 

    @Override
    public void writeFile() {

        String name = getRequiredAttribute(NAME);
        String dir =  getDir();
        String pckg =  getPackage();
        String persistent = getParentAttribute(PERSISTENT, "false");

        initPrintWriter(dir,name+".java");

        write("/* ");
        write(getStoredObject(COMMENT));
        write("*/ ");
        writeLine();
        write("package "+pckg+";");
        write("import de.ama.framework.data.*;");
        writeLine();

        String ext = " implements java.io.Serializable ";
        if("true".equals(persistent)) ext+= ", de.ama.db.PersistentMarker";
        write("public class "+name+" "+ext+" { ");
        writeLine();

        visitChildren(FIELD);
        writeLine();

        visitChildren(COLLECTION);
        writeLine();

        visitChildren(BEAN, new Visitor(){
            public void visit(Tag visitor) {
                String name =  visitor.getRequiredAttribute(NAME);
                String pckg =  visitor.getPackage();
                Java_field f = new Java_field();
                f.setPrintWriter(getPrintWriter());
                f.addAttribute(NAME, Util.firstCharToLower(name));
                f.addAttribute(TYPE, pckg+"."+name);
                f.addAttribute(CREATE, visitor.getAttribute(CREATE));
                f.generate();
            }
        },false);
        writeLine();
        write("}");
        flush();

        registerClass(name,pckg+"."+name);
    }



}