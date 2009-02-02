
package server;

import server.services.Environment;

import java.io.IOException;

/**
 * User: x
 * Date: 22.05.2008
 */
public class Server {

    public static void main(String[] args) {
        Environment.initProduction();
        System.out.println("Server up and running jop");


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}