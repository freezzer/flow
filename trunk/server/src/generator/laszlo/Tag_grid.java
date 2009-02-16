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
public class Tag_grid extends Tag {

    protected void beginWrite() {
        String model = getRequiredParentAttribute(MODEL);
        write(" <grid datapath='local:classroot.data:/list' visible='true' id='"+model+"_grid' width='$once{parent.width-2}'  height='$once{parent.height-2}' >");
    }

    protected void endWrite() {
        String model = getRequiredParentAttribute(MODEL);
        write(" <method name='openEditor' >  <![CDATA[");
        write("   var sel = "+model+"_grid.getSelection();");
        write("   for ( var i=0; i<sel.length; i++ ) {");
        write("      var dp = sel[i];");
        write("      "+model+"_editor_helper.createTab(dp.xpathQuery( 'oid/text()' ));");
        write("   }");
        write("  ]]> </method> ");
        writeLine();
        write(" <method name='deleteBean' >  <![CDATA[");
        write("   var data = new LzDataElement('data'); ");
        write("   var sel = "+model+"_grid.getSelection();");
        write("   for ( var i=0; i<sel.length; i++ ) {");
        write("      var dp = sel[i];");
        write("      var obj = new LzDataElement('"+model+"');");
        write("      var oid = new LzDataElement('oid');");
        write("      oid.appendChild(new LzDataText(dp.xpathQuery('oid/text()')));");
        write("      obj.appendChild(oid);");
        write("      data.appendChild(obj);");
        write("      dp.deleteNode();");
        write("   }");
        write("   ActionService.execute('DeleteBeanAction',data);");
        write("  ]]> </method> ");
        writeLine();
        write(" <method name='refresh' >  <![CDATA[");
        write("   ActionService.execute('QueryBeansAction', classroot.query, classroot.data); ");
        write("  ]]> </method> ");
        write(" </grid>");
        flush();
    }



}