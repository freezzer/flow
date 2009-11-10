package de.ama.generator.java;
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


import de.ama.generator.Tag;
import de.ama.generator.Visitor;
import de.ama.util.Util;

public class Java_permission extends Tag {

    public void writeFile() {
        String name = getRequiredAttribute(NAME);
        String dir =  getDir();
        String pckg =  getPackage();
        initPrintWriter(dir,name+".java");


        write("/* ");
        write(getStoredObject(COMMENT));
        write("*/ ");
        writeLine();
        write("package "+pckg+";");
        writeLine();
        write("import de.ama.services.permission.PermissionSwitch;");
        writeLine();
        write("public class "+name+" extends de.ama.services.permission.PermissionContext { ");
        writeLine();
        write("    @Override");
        write("    protected void addSwitches(){");
        writeLine();
            visitChildren(COMMAND,new Visitor(){
                public void visit(Tag visitor) {
                    String label = visitor.getRequiredAttribute("label");
                    String name = visitor.getAttribute("name","");
                    String use = visitor.getAttribute("use",name);
                    if(Util.isEmpty(use) && Util.isEmpty(name)){
                        throwAttributeException("name or use");
                    }
                    write("           add(new PermissionSwitch(\""+use+" ("+label+")\"));");
                }
            },true);
        write("    }");
        writeLine();
        write("}");
        flush();
    }
}
