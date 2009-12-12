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


public class Flex_provider extends Tag {

    public void generate() {
        String name = getAttribute(NAME);
        String use = getAttribute(USE);
        String path = getAttribute(PATH);
        String query = getAttribute(QUERY);
        String type = getAttribute(TYPE);

        write("        var provider:DataProvider = Factory.createProvider(" + quote(Util.saveToString(use, name))+");");
        if(!isEmpty(path))
        write("        provider.path="+quote(path)+";");
        if(!isEmpty(query))
        write("        provider.query="+quote(query)+";");
        if(!isEmpty(type))
        write("        provider.type="+quote(type)+";");

    }

    @Override
    public void writeFile() {
        String name = getAttribute(NAME);
        if (Util.isNotEmpty(name)) {
            String dir =  getDir();
            String pckg =  getPackage();

            initPrintWriter(dir, name + ".as");
            write("/* ");
            write(getStoredObject(COMMENT));
            write("*/ ");
            writeLine();
            write("package " + pckg + " {");
            write("import de.ama.framework.data.*;");
            write("import de.ama.framework.command.Invoker;");
            write("import de.ama.framework.util.Callback;");
            write("public class " + name + " " + " implements DataProvider { ");
            writeLine();
            write("    private var _invoker:Invoker;");
            write("    private var _cb:Callback;");
            writeLine();
            write("    public function setInvoker(value:Invoker):void {");
            write("         _invoker = value;");
            write("    }");
            write("    ");
            write("    public function getTable(cb:Callback):void {");
            write("        _cb = cb;");
            write("        var array:Array = new Array();");
            write("        _cb.execute(array);");
            write("    } ");
            write("    ");
            write("}}");
            flush();
        }
    }

}