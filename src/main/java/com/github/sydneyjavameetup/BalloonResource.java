package com.github.sydneyjavameetup;

import javax.ws.rs.*;
import java.util.concurrent.atomic.AtomicReference;

@Path("/")
public class BalloonResource {
    private static AtomicReference<Balloon> balloon = new AtomicReference<>(Balloon.of("383e2bb6-fc78-4714-beec-beae939dc3d4", "http://clipart-library.com/data_images/19301.png"));

    @GET
    @Produces("application/json")
    public Balloon get(){
        return balloon.get();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public WriteResult post(Balloon balloon) {
        BalloonResource.balloon.set(balloon);
        return WriteResult.of("Success");
    }
}
