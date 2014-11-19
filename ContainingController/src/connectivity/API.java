/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connectivity;

import org.vertx.java.core.*;
import org.vertx.java.core.http.HttpServerRequest;
import com.google.gson.*;

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
        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
              @Override
              public void handle(HttpServerRequest req) {
                  UpdateMessage m = new UpdateMessage("blah"); //TODO: use more meaningful data
                  req.response().end(gson.toJson(m));
              }
          }).listen(port); // listen on the specified portg (change if needed)
    }
}
