package com.github.sydneyjavameetup;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerConfiguration;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.jersey.JerseySupport;

public class EntryPoint {
    public static void main(String[] args) {
        int portNumber = getPortNumber();
        WebServer.create(
                ServerConfiguration.builder()
                .port(portNumber)
                .build(),

                Routing.builder()
                .register("/",
                        JerseySupport.builder()
                                .register(BalloonResource.class)
                                .build())
                .build())
                .start()
                .thenRun(() -> System.out.println("Running on http://localhost:" + portNumber));
    }

    private static int getPortNumber() {
        String port_number = System.getenv("PORT_NUMBER");
        return port_number == null ? 8080 : Integer.parseInt(port_number);
    }
}
