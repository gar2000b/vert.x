package com.onlineinteract.verticles;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.onlineinteract.application.CSApplication;

import io.vertx.core.AbstractVerticle;

public class CustomerRestServiceVerticle extends AbstractVerticle{
	
	public static final String BASE_URI = "http://0.0.0.0:" + CSApplication.BASE_PORT_NUMBER + "/cs/";
	private HttpServer server;
	
	public void start() {
		
		vertx.executeBlocking(future -> {
			// Call blocking API to set up Jersey Rest Service.
				try {
					System.out.println("Setting up Jersey Rest Service for CS");
					final ResourceConfig rc = new ResourceConfig().packages("com.onlineinteract.rest.resource");
					server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
					System.out.println(String.format("CS Jersey Rest app started with WADL available at "
			                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				future.complete("Jersey Rest Service now running");
			}, false, res -> {
				System.out.println("The result is: " + res.result());
				vertx.close();
			});
	}

	public void stop() {
		// System.out.println("Server shutting down");
		// server.shutdown();
	}
}
