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


package de.ama.generator;

import de.ama.util.UniversalIterator;
import de.ama.util.Util;

/**
 * User: x
 * Date: 23.04.2008
 */
public class Starter {
    private static String[] args;
    private String inFileName, outDirName, targets;

    public Starter() {
        inFileName = getCommandLineParam("inFile", "mandatory");
        outDirName = getCommandLineParam("outDir", "mandatory");
        targets    = getCommandLineParam("targets","mandatory");

        if (Util.isEmpty(targets)) {
            throw new RuntimeException("no targets specified use parameter [targets] with java,flex,...");
        }

        
    }

    public static void main(String[] args) {
       Starter.args = args;
       new Starter().run();
    }

    private void run() {
        new OutputWriter(inFileName, outDirName, targets).start();
    }

    private String getCommandLineParam(String key, String def) {
        UniversalIterator ui = new UniversalIterator(args);
        while (ui.hasNext()) {
            String arg = (String) ui.next();
            if (arg.equalsIgnoreCase(key) && ui.hasNext()) {
                return (String) ui.next();
            }
        }

        if (def.equals("mandatory")) {
            System.out.println("*********************************************************************************");
            System.out.println("*   flow Generate Code ");
            System.out.println("*                                                                             ");
            System.out.println("*   usage:                                        ");
            System.out.println("*   inFile    [input file.xml]        mandatory ! ");
            System.out.println("*   outDir    [output directory]      mandatory ! ");
            System.out.println("*   targets   [geneation targets]     use: java,flex,laszlo,hibernate,to be continued ... ");
            System.out.println("*********************************************************************************");
            System.exit(1);
        }

        return def;
    }


}
