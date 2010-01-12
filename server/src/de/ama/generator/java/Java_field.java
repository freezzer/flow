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


package de.ama.generator.java;


import de.ama.generator.Tag;
import de.ama.util.Util;

/**
 * User: x
 * Date: 25.04.2008
 */
public class Java_field extends Tag {

    public void generate() {
        String name = getRequiredAttribute(NAME);
        String type = getAttribute(TYPE, "String");
        boolean create = getAttribute(CREATE, false);
        boolean reference = getAttribute(REFERENCE, false);
        boolean largeText = false;

        if (isEmpty(type) && !isLeaf()) {
            type = getChild(0).getRequiredAttribute(NAME);
        }
        boolean str = "String".equals(type);
        if ("date".equalsIgnoreCase(type)) {
            type = "String";
            create = false;
            str = true;
        }
        if ("number".equalsIgnoreCase(type)) {
            type = "String";
            create = false;
        }
        if ("text".equalsIgnoreCase(type)) {
            str = true;
            type = "String";
            create = false;
            largeText = true;
        }
        if ("boolean".equalsIgnoreCase(type)) {
            type = "boolean";
            create = false;
        }
        writeLine();
        if (reference) {
            write("    private BoReference<" + type + "> " + name + ";");
            write("    public  BoReference<" + type + "> get" + Util.firstCharToUpper(name) + "() { return " + name + "; }");
            write("    public  void set" + Util.firstCharToUpper(name) + "(BoReference<" + type + "> in) { "+ name + " =in; }");
//            write("    public  " + type + " get" + Util.firstCharToUpper(name) + "() { if(" + name + "==null)return null;  return " + name + ".getBo(); }");
//            String lazy = "if(" + name + "==null){ " + name + " = new BoReference<" + type + ">(); }";
//            write("    public  void   set" + Util.firstCharToUpper(name) + "(" + type + " in) { " + lazy+" "+name + ".setBo(in); }");

        } else {
            String fieldName = name + (largeText ? "_TEXT":"");
            write("    private " + type + " " + fieldName + ";");
            if (str) {
                write("    public  " + type + " get" + Util.firstCharToUpper(name) + "() { return de.ama.util.Util.saveToString(" + fieldName + "); }");
            } else {
                String lazy = "";
                if (create) lazy = "if(" + name + "==null){ " + name + "=new " + type + "(); }";
                write("    public  " + type + " get" + Util.firstCharToUpper(name) + "() { " + lazy + " return " + name + "; }");
            }
            write("    public  void   set" + Util.firstCharToUpper(name) + "(" + type + " in) { " + (getAttribute(MANDATORY, false) ? "check(in,\"" + fieldName + "\"); " : " ") + fieldName + "=in; }");
        }
    }


}