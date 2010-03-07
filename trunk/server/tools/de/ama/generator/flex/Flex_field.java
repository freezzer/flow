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



public class Flex_field extends Tag {

    public void generate() {
        String name = getRequiredAttribute(NAME);
        String type = getAttribute(TYPE,"String");
        boolean reference = getAttribute(REFERENCE, false);

        if(!isLeaf()){
            type = getChild(0).getRequiredAttribute(NAME);
        }
        writeLine();

        if(TEXT.equalsIgnoreCase(type)){    write("    public var "+name+":String;"); }
        else
        if(STRING.equalsIgnoreCase(type)){  write("    public var "+name+":String;"); }
        else
        if(DATE.equalsIgnoreCase(type)){    write("    public var "+name+":String;"); }
        else
        if(NUMBER.equalsIgnoreCase(type)){  write("    public var "+name+":String;"); }
        else
        if(INT.equalsIgnoreCase(type)){     write("    public var "+name+":int;"); }
        else
        if(BOOLEAN.equalsIgnoreCase(type)){ write("    public var "+name+":Boolean;"); }
        else {
            if(reference){
               write("    public var "+name+":BoReference = new BoReference();");
//               write("    public function set "+name+"BoReference(in:BoReference):void{ "+name+" = in;   }");
//               write("    public function get "+name+"BoReference():BoReference       { return "+name+"; }");
            } else {
               write("    public var "+name+":"+type+" = new "+type+"();");
            }
        }

    }


}