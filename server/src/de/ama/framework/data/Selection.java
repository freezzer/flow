/*
 * Selection.java
 *
 * Created on 1. Februar 2003, 23:15
 */

package de.ama.framework.data;

import java.io.Serializable;


/**
 * @author Andreas Marochow
 */
public class Selection implements Serializable {
        private  String className;
        private  int oid;

    public String getClassName() {
        className = className.replace("::",".");
        return className;
    }

    public int getOid() {
        return oid;
    }
}
