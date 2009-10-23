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
import de.ama.util.Util;

/**
 * User: x
 * Date: 25.04.2008
 */
public class Tag_input extends Tag {

    public int getIndent() {
        return 8;
    }


    protected void mainWrite() {
        String label = getRequiredAttribute(LABEL);
        String type = getAttribute(TYPE, "String");
        String path = getRequiredAttribute(PATH);
        int x = getAttribute(X, -1);
        int y = getAttribute(Y, -1);
        String  labelWith = getParentAttribute(LABELWIDTH,"");


        if (STRING.equalsIgnoreCase(type)) {
            write("field = insertTextField(\"" + label + "\",\"" + path + "\"," + x + "," + y + ");");
        } else if (DATE.equalsIgnoreCase(type)) {
            write("field = insertDateField(\"" + label + "\",\"" + path + "\"," + x + "," + y + ");");
        } else if (NUMBER.equalsIgnoreCase(type)) {
            write("field = insertTextField(\"" + label + "\",\"" + path + "\"," + x + "," + y + ");");
        } else if (BOOLEAN.equalsIgnoreCase(type)) {
            write("field = insertBoolField(\"" + label + "\",\"" + path + "\"," + x + "," + y + ");");
        } else if (LOOKUP.equalsIgnoreCase(type)) {
            write("field = insertTextField(\"" + label + "\",\"" + path + "\"," + x + "," + y + ");");
        }

        if(!Util.isEmpty(labelWith)){
            write("field.labelWidth="+labelWith+";");
        }
    }

}