/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nhl.containing.server.network;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nhl.containing.server.util.DataInfo;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxFactory;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;

import com.google.gson.Gson;
import com.jme3.math.Vector3f;

/**
 * The API endpoints of the server, allowing web and android access to required data. Libraries used: Vert.x & Gson.
 * 
 * @author snyx
 */
public final class API
{

	/**
	 * The vert.x object, required to create a server.
	 */
	static Vertx vertx;

	/**
	 * Start the server. TODO: Create a switch to catch different routes, get (and send) more meaningful data, possibly a new class for the data
	 */
	public static void start(int port)
	{
		vertx = VertxFactory.newVertx();
		final Gson gson = new Gson();
		RouteMatcher rm = new RouteMatcher();
		rm.get("/", new Handler<HttpServerRequest>()
		{
			@Override
			public void handle(HttpServerRequest req)
			{
				DataInfo info = new DataInfo();
				info.fillData("1", "2", "3", "4", "5", "6");
				req.response().end(gson.toJson(info));
			}
		});
		rm.get("/:id", new Handler<HttpServerRequest>()
		{
			@Override
			public void handle(HttpServerRequest req)
			{
				req.response().end(gson.toJson(req.params().get("id")));
			}
		});
		vertx.createHttpServer().requestHandler(rm).listen(port); // listen on the specified portg (change if needed)
	}
}
