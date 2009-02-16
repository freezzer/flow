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

/**
 * User: x
 * Date: 25.04.2008
 */

public class Tag_editor extends Tag {
    protected void mainWrite() {
        String model = getRequiredParentAttribute(MODEL);
        String title = getParentAttribute(TITLE,model+" Editor");

        writeLine();
        write("<!-- ******************************** "+model+"_editor ******************************** --> ");
        write("<node name='"+model+"_editor_helper' > ");
        write("  <method name='createTab' args='oid'>  <![CDATA[");
        write("    p = new lz.customtabpane(mainTabs,{text:'"+title+"'});");
        write("    e = new lz."+model+"_editor(p);");
        write("    if(oid!=null && oid.length>0) e.setOid(oid);");
        write("  ]]> </method>");
        write("</node>");
        write("<class name='"+model+"_editor' >");
        write(" <dataset name='data'>");
        write("    <"+model+">");
        write("     <oid>0</oid>");
        write("    </"+model+">");
        write(" </dataset>");
        write(" <datapointer name='mdp' xpath='data:"+model+"/oid' />");
        write(" <method name='setOid' args='oid'>");
        write("    mdp.setXPath('local:data:/"+model+"/oid');");
        write("    mdp.setNodeText(oid); ");
        write("    ActionService.execute('LoadBeanAction', this.data, this.data);");
        write(" </method>");
    }


    protected void endWrite() {
        write("</class>");
        flush();
    }




}