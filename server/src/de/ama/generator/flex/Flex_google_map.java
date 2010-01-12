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



public class Flex_google_map extends Tag {

    public void generate() {
        int x  = getAttribute(X,10);
        int y  = getAttribute(Y,10);
        int w  = getAttribute(W,200);
        int h  = getAttribute(H,200);
        write("        var map:GoogleMap = GoogleMap(addChild(new GoogleMap("+x+", "+y+", "+w+", "+h+")));");
        if(hasAttribute("countryField")){
            write("        map.countryField=getEditField("+quote(getAttribute("countryField"))+");");
        }
        if(hasAttribute("cityField")){
            write("        map.cityField=getEditField("+quote(getAttribute("cityField"))+");");
        }
        if(hasAttribute("zipField")){
            write("        map.zipField=getEditField("+quote(getAttribute("zipField"))+");");
        }
        if(hasAttribute("streetField")){
            write("        map.streetField=getEditField("+quote(getAttribute("streetField"))+");");
        }
    }


}