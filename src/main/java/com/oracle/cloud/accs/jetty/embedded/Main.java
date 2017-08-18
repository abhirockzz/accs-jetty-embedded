package com.oracle.cloud.accs.jetty.embedded;

import java.net.URI;
import java.util.Optional;
import javax.websocket.server.ServerContainer;
import javax.ws.rs.core.UriBuilder;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public final class Main {

    public static void main(String[] args) throws Exception {
        String hostname = Optional.ofNullable(System.getenv("HOST")).orElse("localhost");
        String port = Optional.ofNullable(System.getenv("PORT")).orElse("8080");

        URI baseUri = UriBuilder.fromUri("http://" + hostname + "/").port(Integer.parseInt(port)).build();

        ResourceConfig config = new ResourceConfig(StockPriceResource.class);
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(Integer.valueOf(port));
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");

        ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(context);
        wscontainer.addEndpoint(RealTimeStockTicker.class);

        Broadcaster broadcaster = new Broadcaster();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Exiting");
                try {
                    server.destroy();
                    System.out.println("Jetty stopped");

                    broadcaster.stop();
                    System.out.println("Broadcast stopped");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }));
        server.start();
        System.out.println("Application accessible at " + baseUri.toString());

        broadcaster.start();
        System.out.println("broadcast started..");

        server.join();
    }
}
