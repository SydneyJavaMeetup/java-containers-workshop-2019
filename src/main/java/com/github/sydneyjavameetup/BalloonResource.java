package com.github.sydneyjavameetup;

import javax.ws.rs.*;
import java.util.List;

@Path("/")
public class BalloonResource {
    private BalloonDBAccess database;

    public BalloonResource() {
        this.database = new BalloonDynamoAccess();
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public List<Balloon> get(){
        return database.listBalloons();
    }

    @GET
    @Path("/{id}/")
    @Produces("application/json")
    public Balloon get(@PathParam("id") String id){
        return database.getBalloon(id);
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public WriteResult save(Balloon balloon) {
        database.saveBalloon(balloon);
        return WriteResult.of("Success");
    }

    @DELETE
    @Path("/{id}/")
    @Consumes("application/json")
    @Produces("application/json")
    public WriteResult delete(@PathParam("id") String id) {
        database.deleteBalloon(id);
        return WriteResult.of("Success");
    }
}
