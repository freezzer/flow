
package server;

import server.services.Environment;

/**
 * User: x
 * Date: 22.05.2008
 */
public class Bootstrap {

    public Bootstrap() {
        Environment.initProduction();
        System.out.println("FLOW-Server up and running OK.");
    }

}