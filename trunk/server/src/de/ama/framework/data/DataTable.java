
package de.ama.framework.data;

import de.ama.util.Util;

import java.util.ArrayList;


public class DataTable extends ArrayList{

    private String templateClassName=null;

    public DataTable() {
    }

    public DataTable(String templateClassName) {
        this.templateClassName = templateClassName;
    }

    public String asXMLString(boolean printFormat) throws IllegalAccessException {
        Data.level++;
        StringBuffer sb = new StringBuffer();
        String indent="";
        for(int x=0;x<Data.level;x++)
            indent +="  ";

        sb.append(indent+"<DataTable>"+Util.CRLF);

        for (int i = 0; i < size(); i++) {
            Data data = (Data) get(i);
            sb.append( data.asXMLString("element",printFormat) );
        }
        sb.append(indent+"</DataTable>").append(Util.CRLF);
        Data.level--;
        return sb.toString();
    }


    public void add(Data data) {
        super.add(data);
    }

    @Override
    public String toString() {
        return "DataTable size="+size();
    }
}
