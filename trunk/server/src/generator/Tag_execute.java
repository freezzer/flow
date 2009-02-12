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


package generator;

/**
 * User: x
 * Date: 25.04.2008
 */
public class Tag_execute extends Tag {

    protected int getIndent() {
        return 4;
    }

    protected void mainWrite() {
        boolean multi = getAttribute("multi",true);
        String filter = getAttribute("filter","");

    if(filter.length()>0)
        write("if(!data.hasName(\""+filter+"\")) return true;");

        write("try{");
        write("  " + getText().trim());
        write("} catch (Exception e) {  ");
        write("  addError(e.getMessage()); ");
        write("  e.printStackTrace(); ");
        write("  rollback(); ");
        write("}");
        write("return "+multi+";");
    }
}