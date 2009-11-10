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


public class Flex_input extends Tag {

    public void generate() {
        String label = getRequiredAttribute(LABEL);
        String type = getAttribute(TYPE, "String");
        String path = getRequiredAttribute(PATH);
        int x = getAttribute(X, -1);
        int y = getAttribute(Y, -1);
        String  labelWith = getParentAttribute(LABELWIDTH,"");

        String xy="";
        if(x>=0 || y>=0){
            xy= " ," + x + "," + y;
        }
        if (STRING.equalsIgnoreCase(type)) {
            write("        field = insertTextField(\"" + label + "\",\"" + path + "\"" + xy+ ");");
        } else if (DATE.equalsIgnoreCase(type)) {
            write("        field = insertDateField(\"" + label + "\",\"" + path + "\"" + xy+ ");");
        } else if (NUMBER.equalsIgnoreCase(type)) {
            write("        field = insertTextField(\"" + label + "\",\"" + path + "\"" + xy+ ");");
        } else if (BOOLEAN.equalsIgnoreCase(type)) {
            write("        field = insertBoolField(\"" + label + "\",\"" + path + "\"" + xy+ ");");
        } else if (LOOKUP.equalsIgnoreCase(type)) {
            write("        field = insertTextField(\"" + label + "\",\"" + path + "\"" + xy+ ");");
        } else if (LIST.equalsIgnoreCase(type)) {
            String lister = isLeaf() ? getRequiredAttribute(LISTER) : getChild(0).getAttribute(NAME);
            write("        field = insertListField(\"" + label + "\",\"" + path + "\",\"" + lister +"\""+ xy+ ");");
        } else if (AREA.equalsIgnoreCase(type)) {
            write("        field = insertTextAreaField(\"" + label + "\",\"" + path + "\"" + xy+ ");");
        }

        if(!Util.isEmpty(labelWith)){
            write("        field.labelWidth="+labelWith+";");
        }
    }

}