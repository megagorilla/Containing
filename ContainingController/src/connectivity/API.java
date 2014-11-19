/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connectivity;

import org.vertx.java.core.*;
import org.vertx.java.core.http.HttpServerRequest;
import com.google.gson.*;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.vertx.java.core.http.RouteMatcher;

/**
 * The API endpoints of the server, allowing web and android access to required data.
 * Libraries used: Vert.x & Gson.
 * @author snyx
 */
public final class API {
    
    /**
     * The vert.x object, required to create a server.
     */
    static Vertx vertx;
    
    /**
     * Start the server.
     * TODO: Create a switch to catch different routes, get (and send) more meaningful data, possibly a new class for the data
     */
    public static void start(int port) {
        vertx = VertxFactory.newVertx();
        final Gson gson = new Gson();
        RouteMatcher rm = new RouteMatcher();
        rm.get("/", new Handler<HttpServerRequest>() {
              @Override
              public void handle(HttpServerRequest req) {
                Set set = new HashSet<>();
                UpdateMessage m = new UpdateMessage("blah", set); //TODO: use more meaningful data
                m.addData("test", Vector3f.ZERO, Quaternion.IDENTITY);
                req.response().end(gson.toJson(m));
              }
        });
        rm.get("/:id", new Handler<HttpServerRequest>() {
            @Override
            public void handle(HttpServerRequest req) {
                req.response().end(gson.toJson(req.params().get("id")));
            }
        });
        vertx.createHttpServer().requestHandler(rm).listen(port); // listen on the specified portg (change if needed)
    }
}
