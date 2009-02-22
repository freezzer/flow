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


package generator.flex;

import generator.Tag;

/**
 * User: x
 * Date: 25.04.2008
 */
public class Tag_menu extends Tag {

    protected void beginWrite() {
        String name = getRequiredAttribute(NAME);
        write("  <menu label='"+name+"'>");
    }

    protected void endWrite() {
        write("  </menu>");
    }

}