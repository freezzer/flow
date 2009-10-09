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


package de.ama.generator.laszlo;

import de.ama.generator.Tag;

/**
 * User: x
 * Date: 25.04.2008
 *
 *     <class name="Auflister" extends="view">
        <dataset name="tabledata">
          <query>
             <type> Person </type>
             <!--<statement path="vorname" op="EQ" value="Herb*,RO*" />-->
          </query>
        </dataset>

        <simplelayout spacing="5" axis="y"/>
        <grid height="400" width="400" datapath="local:parent.tabledata:/list" >
            <gridtext datapath="position()" editable="false"> Nr. </gridtext>
            <gridtext datapath="vorname/text()" editable="true"> Vorname </gridtext>
            <gridtext datapath="nachname/text()" editable="false"> Nachname </gridtext>
            <gridtext datapath="telefon/text()" editable="false"> Telefon </gridtext>
        </grid>

        <view x="110">
           <simplelayout spacing="3" axis="x"/>
           <button x="230" text="reload" onclick="ActionService.execute('QueryBeansAction', parent.parent.tabledata)" />
        </view>

    </class>


 */
public class Tag_lister extends Tag {

    protected void beginWrite() {
        String model = getRequiredParentAttribute(MODEL);
        String title = getParentAttribute(TITLE,model+" Editor");

        writeLine();
        write("<!-- ******************************** "+model+"_lister ******************************** --> ");
        write("<node name='"+model+"_lister_helper' > ");
        write("  <method name='createTab' >");
        write("    p = new lz.customtabpane(mainTabs,{text:'"+title+"'});");
        write("    l = new lz."+model+"_lister(p);");
        write("    Person_grid.refresh();");
        write("  </method>");
        write("</node>");
        write("<class name='"+model+"_lister' extends='view' >");
        write("  <dataset name='query'>");
        write("   <query>");
        write("     <type>"+model+"</type>");
        write("   </query>");
        write("  </dataset>");
        write("  <dataset name='data' />");
        write("  <view width='${canvas.width}' height='${canvas.height-35}'>");
        write("   <stableborderlayout axis='y'/>");
//        write("   <view name='toolbar' id='"+model+"_lister_toolbar' height='20' width='${parent.width}' />");
    }


    protected void endWrite() {
        String model = getRequiredParentAttribute(MODEL);
        write("   <view name='statusbar' id='"+model+"_lister_statusbar' height='20' width='$once{parent.width}' />");
        write("  </view>");
        write("</class>");
        flush();
    }
}