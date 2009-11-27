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
import de.ama.generator.VisitorGenerate;
import de.ama.util.Util;



public class Flex_lister extends Tag {

    public void writeFile() {
        String label = getAttribute(LABEL);
        String type = getRequiredAttribute(TYPE);
        String listerName = getParentAttribute(NAME,type+"Lister");
        String dir =  getDir();
        String pckg =  getPackage();

        initPrintWriter(dir,listerName+".as");

        write("package "+pckg+" {");
        write("import de.ama.framework.data.BusinessObject;");
        write("import de.ama.framework.gui.frames.ListPanel;");
        write("import de.ama.services.Factory;");
        write("import de.ama.framework.command.*;");
        write("     ");
        write("public class "+listerName+" extends ListPanel {");
        write("     ");
        write("     override public function createData():BusinessObject {");
        write("        return Factory.createBean(\""+type+"\"); ");
        write("     } ");
        write("");
        write("     override public function addCommands():void {");
        write("        var cmd:Command;");
                         visitChildren(COMMAND, new Visitor(){
                             public void visit(Tag visitor) {
                                 visitor.generate();
                                 write("        addCommand(cmd);");
                             }
                         },false);
        write("     } ");
        write("");
        write("     override public function addCollumns():void {");
        if(!Util.isEmpty(label))
        write("        label = \""+label+"\"");
                       visitChildren(COLUMN);
        write("     } ");
        write("}}");
        writeLine();
        flush();

    }


}