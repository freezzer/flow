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

public class Java_bootstrap extends Tag {

     @Override
    public void writeFile() {
        String name = getAttribute(NAME,"Bootstrap");

        String dir =  getDir();
        String pckg =  getPackage();
        initPrintWriter(dir,name+".java");

        write("/* ");
        write(getStoredString(COMMENT));
        write("*/ ");
        writeLine();
        write("package "+pckg+";");
        writeLine();
        write("import de.ama.services.Environment;");
        write("import de.ama.services.PermissionService;");
        write("import de.ama.services.EventService;");
        writeLine();
        write("public class "+name+" { ");

        writeCodeBlock("registerBeans");
        write("    protected void registerBeans(){");
        writeLine();
        visitAllChildren(BEAN, new Visitor(){
             public void visit(Tag visitor) {
                 String name = visitor.getRequiredAttribute(NAME);
                 write("         Environment.registerBean(\""+name+"\", "+visitor.getPackage()+"."+name+".class);");
             }
         });
        write("    }");

        writeCodeBlock("registerPermissions");
        write("    protected void registerPermissions(){");
        writeLine();
         visitAllChildren(PERMISSION, new Visitor(){
              public void visit(Tag visitor) {
                  String name = visitor.getRequiredAttribute(NAME);
                  write("         Environment.getPermissionService().registerPermissionContext(\""+name+"\", "+visitor.getPackage()+".Permission"+name+".class);");
              }
          });
        write("    }");

        writeCodeBlock("registerEventConsumer");
        write("    protected void registerEventConsumer(){");
        writeLine();
         visitAllChildren(EVENT_CONSUMER, new Visitor(){
              public void visit(Tag visitor) {
                  String name = visitor.getRequiredAttributeAlternative(NAME,USE);
                  String events = visitor.getRequiredAttribute(EVENTS);
                  write("         Environment.getEventService().registerConsumer(new "+visitor.getPackage()+"."+name+"("+quote(events)+"));");
              }
          });
        write("    }");

        writeCodeBlock("preMain");
        write("    public void preMain() {");
        write("         registerBeans();");
        write("         registerPermissions();");
        write("         registerEventConsumer();");
        write("         System.out.println(\"Bootstrap done OK\");");
        write("    }");
        writeManualBLock();
        write("}");
        flush();

    }


}