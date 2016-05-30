package com.onlineinteract.application;

import java.util.UUID;

import com.onlineinteract.verticles.CustomerRestServiceVerticle;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class CSApplication {

	private String deploymentID;
	private Vertx vertx;
	public static String BASE_PORT_NUMBER = "8085";
	public static UUID APP_ID = UUID.randomUUID();

	public CSApplication() {
		
		vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));

		vertx.deployVerticle(new CustomerRestServiceVerticle(), res -> {
			if (res.succeeded()) {
				deploymentID = res.result();
				System.out.println("Deployment id is: " + res.result());
			} else {
				System.out.println("Deployment failed!");
			}
		});
	}

	@SuppressWarnings("unused")
	private void undeployVerticles() {
		System.out.println("Do we even get here");
		vertx.undeploy(deploymentID, res -> {
			System.out.println("* undeploying verticles");
			if (res.succeeded()) {
				System.out.println("Undeployed ok");
			} else {
				System.out.println("Undeploy failed!");
			}
		});
	}
	
	public static void main(String[] args) {
		
		System.out.println("Running Customer Service (CS) Application with UUID of " + APP_ID);
		new CSApplication();
	}
}
