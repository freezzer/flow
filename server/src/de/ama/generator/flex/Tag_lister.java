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

/**
 * User: x
 * Date: 25.04.2008
 */

public class Tag_lister extends Tag {
    @Override
    protected int getIndent() {
        return 0;
    }

    @Override
    protected void beginWrite() {
        String model = getRequiredAttribute(MODEL);
        String listerName = getParentAttribute(NAME,model+"Lister");
        String dir =  getDir();
        String pckg =  getPackage();

        initPrintWriter(dir,listerName+".as");

        write("package "+pckg+" {");
        write("import de.ama.framework.data.Data;");
        write("import de.ama.framework.gui.frames.ListPanel;");
        write("import de.ama.framework.util.Factory;");
        write("import de.ama.framework.command.*;");
        write("     ");
        write("public class "+listerName+" extends ListPanel {");
        write("     ");
        write("     override public function createData():Data {");
        write("         return Factory.createBean(\""+model+"\"); ");
        write("     } ");
        write("     ");
        write("     override public function addBehavior():void { ");
        write("     var cmd:Command = null;");

        collectCode(Tag_bootstrap.FORCE_IMPORT, "import "+pckg+"."+listerName+";");
        collectCode(Tag_bootstrap.REGISTER_LISTER, "         Factory.registerLister(\""+listerName+"\", "+listerName+");");

    }

    protected void mainWrite() {
        // hier kommen die Commands
        // hier kommen die Columns
    }


    protected void endWrite() {
        write("     } ");
        write("  }");
        write("}");

        writeLine();
        flush();
    }




}