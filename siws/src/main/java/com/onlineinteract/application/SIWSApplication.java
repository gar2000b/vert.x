package com.onlineinteract.application;

import com.onlineinteract.morphia.MorphiaAppDriver;
import com.onlineinteract.verticles.SocialInsuranceRestServiceVerticle;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class SIWSApplication {

	private String deploymentID;
	private Vertx vertx;
	public static String CS_ADDRESS;
	public static String BASE_PORT_NUMBER;

	public SIWSApplication() {
		// Initiate Morphia Driver before proceeding.
		MorphiaAppDriver.getInstance();
		
		vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));

		vertx.deployVerticle(new SocialInsuranceRestServiceVerticle(), res -> {
			if (res.succeeded()) {
				deploymentID = res.result();
				System.out.println("Deployment id is: " + res.result());
			} else {
				System.out.println("Deployment failed!");
			}
		});
	}

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
		
		if(args.length > 0 && args[0] != null && args[0] != ""){
			BASE_PORT_NUMBER = args[0];
		} else {
			BASE_PORT_NUMBER = "8080";
		}
		
		if(args.length == 2 && args[1] != null && args[1] != ""){
			CS_ADDRESS = args[1];
		} else {
			CS_ADDRESS = "localhost:8081";
		}
		
		System.out.println("Running Social Insurance Workflow Service (SIWS) Application. CS Address is: " + CS_ADDRESS);
		new SIWSApplication();
	}
}
