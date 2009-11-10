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
import de.ama.generator.Visitor;
import de.ama.util.Util;

public class Flex_permission extends Tag {
    public void writeFile() {
        String name = getRequiredAttribute(NAME);
        String dir =  getDir();
        String pckg =  getPackage();
        initPrintWriter(dir,name+".as");


        write("/* ");
        write(getStoredObject(COMMENT));
        write("*/ ");
        writeLine();
        write("package "+pckg+" {");
        write("import de.ama.services.permission.*;");

        write("public class "+name+" "+" extends PermissionContext { ");
        writeLine();
        write("}}");
        flush();
    }


}