


package de.ama.generator;

import de.ama.util.Util;
import de.ama.util.UniversalIterator;


/**
 * User: Andreas Marochow
 * Die Klasse mit dem main() fuer den Generator.
 */
public class Starter {
    private static String[] args;
    private String inFileName, root_package, targets;

    public Starter() {
        inFileName = getCommandLineParam("inFile", "mandatory");
        root_package = getCommandLineParam("root_package", "mandatory");
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
        new OutputWriter(inFileName, root_package, targets).start();
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
            System.out.println("*   inFile       [input file.xml]        mandatory ! ");
            System.out.println("*   root_package [root_package]          mandatory !, your company name ");
            System.out.println("*   targets      [geneation targets]     use: java,flex,hibernate,to be continued ... ");
            System.out.println("*********************************************************************************");
            System.exit(1);
        }

        return def;
    }


}
