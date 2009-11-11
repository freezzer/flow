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


public class Flex_bootstrap extends Tag {

    @Override
    public void writeFile() {

        String name = getAttribute(NAME,"Bootstrap");
        String dir =  getDir();
        String pckg =  getPackage();
        initPrintWriter(dir,name+".as");

        write("/* ");
        write(getStoredObject(COMMENT));
        write("*/ ");
        writeLine();
        write("package "+pckg+" {");
        write("import de.ama.services.Factory;");
        write("public class "+name+" {");
        write("");
        write("    public function execute():void {");
                    visitAllChildren(BEAN,new Visitor(){
                        public void visit(Tag visitor) {
                            String name = visitor.getRequiredAttribute(NAME);
                            String pckg = visitor.getPackage();
                            write("        import " + pckg + "." + name + ";");
                            write("        Factory.registerBean(\"" + name + "\", " +pckg+"."+ name + ");");
                        }
                    });
                    visitAllChildren(COMMAND,new Visitor(){
                        public void visit(Tag visitor) {
                            String name = visitor.getAttribute(NAME);
                            if(Util.isEmpty(name)) return;
                            
                            String pckg = visitor.getPackage();
                            write("        import " + pckg + "." + name + ";");
                            write("        Factory.registerCommand(\"" + name + "\", " + pckg+"."+ name + ");");
                        }
                    });
                    visitAllChildren(PANEL,new Visitor(){
                        public void visit(Tag visitor) {
                            String name = visitor.getAttribute(NAME);
                            if(Util.isEmpty(name)) return;
                            String pckg = visitor.getPackage();
                            write("        import " + pckg + "." + name + ";");
                            write("        Factory.registerPanel(\"" + name + "\", " + pckg+"."+ name + ");");
                        }
                    });
                    visitAllChildren(EDITOR,new Visitor(){
                        public void visit(Tag visitor) {
                            String name = visitor.getRequiredAttribute(NAME);
                            String pckg = visitor.getPackage();
                            write("        import " + pckg + "." + name + ";");
                            write("        Factory.registerEditor(\"" + name + "\", " + pckg+"."+ name + ");");
                        }
                    });
                    visitAllChildren(TREE_EDITOR,new Visitor(){
                        public void visit(Tag visitor) {
                            String name = visitor.getRequiredAttribute(NAME);
                            String pckg = visitor.getPackage();
                            write("        import " + pckg + "." + name + ";");
                            write("        Factory.registerEditor(\"" + name + "\", " + pckg+"."+ name + ");");
                        }
                    });
                    visitAllChildren(LISTER,new Visitor(){
                        public void visit(Tag visitor) {
                            String name = visitor.getAttribute(NAME);
                            if(Util.isEmpty(name)) return;
                            String pckg = visitor.getPackage();
                            write("        import " + pckg + "." + name + ";");
                            write("        Factory.registerLister(\"" + name + "\", " + pckg+"."+ name + ");");
                        }
                    });
                    visitAllChildren(PERMISSION,new Visitor(){
                        public void visit(Tag visitor) {
                            String name = visitor.getRequiredAttribute(NAME);
                            String pckg = visitor.getPackage();
                            write("        import " + pckg + ".Permission" + name + ";");
                            write("        Factory.registerPermission(\"" + name + "\", " +pckg+".Permission"+ name + ");");
                        }
                    });
        write("    }");
        write("");
        write("}}");
        flush();
    }


}