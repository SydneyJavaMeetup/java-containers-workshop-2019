package com.github.sydneyjavameetup;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerConfiguration;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.jersey.JerseySupport;

public class EntryPoint {
    public static void main(String[] args) {
        WebServer.create(
                ServerConfiguration.builder()
                .port(8080)
                .build(),

                Routing.builder()
                .register("/",
                        JerseySupport.builder()
                                .register(BalloonResource.class)
                                .build())
                .build())
                .start()
                .thenRun(() -> System.out.println("Running on http://localhost:8080"));
    }
}
