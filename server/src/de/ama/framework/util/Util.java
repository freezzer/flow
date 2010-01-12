package de.ama.framework.util;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 18.04.2009
 * Time: 23:31:37
 * To change this template use File | Settings | File Templates.
 */
public class Util {

    public static String replaceSubString(String gesamt, String oldString, String newString) {
        String result = "";
        if (gesamt == null) return gesamt;
        if (newString == null) newString = "";
        if (oldString == null) oldString = "";
        if (oldString.equals("")) return gesamt;
        if (oldString.equals(newString)) return gesamt;

        int indexOldStr = gesamt.indexOf(oldString);
        int oldStringLength = oldString.length();
        while (indexOldStr > -1) {
            result += gesamt.substring(0, indexOldStr) + newString;
            gesamt = gesamt.substring(indexOldStr + oldStringLength);
            indexOldStr = gesamt.indexOf(oldString);
        }
        result += gesamt;
        return result;
    }

    public static String normalizeFileName(String in){
        String ret = replaceSubString(in," ","_");
        return ret;
    }
}
