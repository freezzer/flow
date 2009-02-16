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


package generator.laszlo;

import generator.Tag;

import java.io.PrintWriter;
import java.util.List;

/**
 * User: x
 * Date: 25.04.2008
 */
public class Tag_application extends Tag {
    
    protected void beginWrite() {
        addAttribute("color","white");

        String name = getAttribute(NAME,"application");
        String dir =  getParentAttribute(DIR,"");
        initPrintWriter(dir,name+".lzx");

        write("<!-- ");
        write(getStoredObject(COMMENT));
        write("--> ");
        write("<canvas height='100%' width='100%' >");
        write(" <include href='components.lzx'/>");

        checkHref(getPrintWriter() , (Tag) getRoot());
    }

    protected void endWrite() {
        write("</canvas>");
        flush();
    }

    private void checkHref(PrintWriter pw , Tag tag){
        if(tag.getName().equals("view")){
            String tmp = tag.getParentAttribute(MODEL,"");
                   tmp  = tag.getParentAttribute(NAME,tmp);
            write(" <include href='"+tmp+".lzx"+"'/>");
        }
        List children = tag.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Tag child = (Tag) children.get(i);
            checkHref(pw,child);
        }
    }

}
