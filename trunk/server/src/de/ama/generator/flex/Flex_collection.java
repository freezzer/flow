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


public class Flex_collection extends Tag {

    public void generate() {
        String pckg =  getPackage();
        String name = getRequiredAttribute(NAME);
        String type = getAttribute(TYPE);
        int create = getAttribute(CREATE, 0);
        if(isEmpty(type) && !isLeaf()){
           type = getAttribute(TYPE, getChild(0).getAttribute(NAME));
        }

        writeLine();
        if( create>0 ){
            write("    public var "+name+":Array = Util.createArray(\""+pckg+"."+type+"\","+create+");");
        } else {
            write("    public var "+name+":Array = new Array();");
        }
    }


}