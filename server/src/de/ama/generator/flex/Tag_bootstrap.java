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
public class Tag_bootstrap extends Tag {
    protected static final String FORCE_IMPORT = "FORCE_IMPORT";
    protected static final String REGISTER_IN_FACTORY = "REGISTER_IN_FACTORY";

    protected void beginWrite() {

        String name = getAttribute(NAME,"Bootstrap");
        String dir =  getParentAttribute(DIR,"");
        dir =  getParentAttribute(FLEX_DIR,dir);
        String pckg =  getParentAttribute(FLEX_PACKAGE,"");
        initPrintWriter(dir,name+".as");

        write("/* ");
        write(getStoredObject(COMMENT));
        write("*/ ");
        writeLine();
        write("package "+pckg+" {");
        write("import de.ama.framework.util.Factory;");
        write(getCollectedCode(FORCE_IMPORT));
        write("");
        write("public class "+name+" {");
        write("");
        write("    public function execute():void {");
        write(getCollectedCode(REGISTER_IN_FACTORY));
        write("    }");
        write("");
        write("}}");
    }

    @Override
    protected int getIndent() {
        return 0;
    }

    protected void endWrite() {
        write("");
        flush();
    }

}