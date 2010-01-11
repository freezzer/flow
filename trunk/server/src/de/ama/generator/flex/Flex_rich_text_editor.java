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


public class Flex_rich_text_editor extends Tag {

    public void generate() {
        String label = getRequiredAttribute(LABEL);
        boolean editable = getAttribute(EDITABLE, true);
        boolean bulletButton = getAttribute(BULLET_BUTTON, true);
        boolean alignButtons = getAttribute(ALIGN_BUTTONS, true);
        boolean colorPicker = getAttribute(COLOR_PICKER, true);
        boolean linkInput = getAttribute(LINK_INPUT, true);

        String path = getRequiredAttribute(PATH);
        String def = getAttribute(DEFAULT);
        int x = getAttribute(X, -1);
        int y = getAttribute(Y, -1);
        int w = getAttribute(W, -1);
        int h = getAttribute(H, -1);
        String  lw = getParentAttribute(LABELWIDTH,"");

        String xylw="";
        if(x>=0 || y>=0){
            xylw= " ," + x + "," + y;
            if(!isEmpty(lw)){
                xylw += ","+lw;
            }
        }
        String width="";
        if(w >=0 ){
            width= " ," + w ;
        }
        String height="";
        if(h>=0){
            height= "," + h;
        }

        write(" ");
        write("        field = insertRichTextEditorField(\"" + label + "\",\"" + path + "\"" + xylw+width+height+ ");");

        if(!editable)    {    write("        field.editable=false;");       }
        if(!isEmpty(def)){    write("        field.defaultValue="+quote(def)+";");        }
        if(!linkInput)   {    write("        RichTextEditorField(field).enableLinkInput=false;");        }
        if(!bulletButton){    write("        RichTextEditorField(field).enableBulletButton=false;");        }
        if(!alignButtons){    write("        RichTextEditorField(field).enableAlignButtons=false;");        }
        if(!colorPicker) {    write("        RichTextEditorField(field).enableColorPicker=false;");        }
    }

}