package de.ama;

import de.ama.framework.servlet.DownloadServlet;
import de.ama.framework.servlet.UploadServlet;
import de.ama.services.Environment;
import de.ama.services.impl.ActionServiceImpl;
import de.ama.util.Ini;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerList;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;
import flex.messaging.MessageBrokerServlet;
import flex.messaging.io.ClassAliasRegistry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 16.04.2009
 * Time: 09:36:31
 * To change this template use File | Settings | File Templates.
 */
public class Starter {

    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.start();
    }

    /**
     * data   ---+- aaaa
     *           +- bbbbbb
     *
     * webapp ---+- static
     *           |    +-   history
     *           |    ... content
     *           +- view
     *           |    +-   click
     *           |    ... content
     *
     */

    private void start() {


        try {
            // DB Connection ....
            de.ama.services.Environment.initProduction();
            int port       = Ini.getInt("server.port",8080               ,"http port where this server is listening");

            String context = Ini.getString("server.context","/flow"       ,"http context/doc-base of this server");

            Server server = new Server(port);

            ResourceHandler data_handler = new ResourceHandler();
            data_handler.setResourceBase(Environment.getHomeRelativDir("data").getAbsolutePath());

            ResourceHandler static_handler = new ResourceHandler();
            static_handler.setResourceBase(Environment.getHomeRelativDir("webapp").getAbsolutePath());

            // Servlet Contexts are here
            String rootdir = Environment.getHomeRelativDir("webapp/pages").getAbsolutePath();
            Context servlet_handler = new Context(server, context, Context.SESSIONS);
            servlet_handler.setResourceBase(rootdir);
            servlet_handler.addServlet(new ServletHolder(new DownloadServlet()), "/download/*");
            servlet_handler.addServlet(new ServletHolder(new UploadServlet()), "/upload/*");
            servlet_handler.addServlet(new ServletHolder(new org.apache.click.ClickServlet()), "*.htm");

            // Hessian
            // servlet_handler.addServlet(new ServletHolder(new ActionServiceImpl()), "/action/*");

            // BlazeDS
            MessageBrokerServlet messageBrokerServlet = new MessageBrokerServlet();
            servlet_handler.addServlet(new ServletHolder(messageBrokerServlet), "/messagebroker/*");
            ClassAliasRegistry.getRegistry().registerAlias("de.ama.framework.data.BusinessObject","de.ama.framework.data.BusinessObject");


            // put it all together
            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{ static_handler, data_handler, servlet_handler, new DefaultHandler()});
            server.setHandler(handlers);
            
            // and go
            server.start();
//            server.join();

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}

