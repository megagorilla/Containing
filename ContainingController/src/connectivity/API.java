/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connectivity;

import org.vertx.java.core.*;
import org.vertx.java.core.http.HttpServerRequest;
import com.google.gson.*;

/**
 *
 * @author snyx
 */
public class API {
    static Vertx vertx;
    public static void start() {
        vertx = VertxFactory.newVertx();
        final Gson gson = new Gson();
        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
              @Override
              public void handle(HttpServerRequest req) {
                  UpdateMessage m = new UpdateMessage("blah");
                  req.response().end(gson.toJson(m));
              }
          }).listen(8080);
    }
}
