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
public class Tag_application extends Tag {

    protected void beginWrite() {
        String w = getAttribute(W, "100%");
        String h = getAttribute(H, "100%");

        String name = getAttribute(NAME,"application");
        String dir =  getParentAttribute(DIR,"");
        initPrintWriter(dir,name+".mxml");

        write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        write("<mx:Application xmlns:mx=\"http://www.adobe.com/2006/mxml\" xmlns:comps=\"components.*\" layout=\"vertical\" " +
                "width='"+w+"' height='"+h+"' paddingTop='5' paddingLeft='5' paddingRight='5' paddingBottom='5' >");
//        checkHref(getPrintWriter() , (Tag) getRoot());
    }

    @Override
    protected int getIndent() {
        return 0;
    }

    protected void endWrite() {
        write("</mx:Application>");
        flush();
    }

//    private void checkHref(PrintWriter pw , Tag tag){
//        if(tag.getName().equals("view")){
//            String tmp = tag.getParentAttribute(MODEL,"");
//                   tmp  = tag.getParentAttribute(NAME,tmp);
//            write(" <include href='"+tmp+".lzx"+"'/>");
//        }
//        List children = tag.getChildren();
//        for (int i = 0; i < children.size(); i++) {
//            Tag child = (Tag) children.get(i);
//            checkHref(pw,child);
//        }
//    }

}