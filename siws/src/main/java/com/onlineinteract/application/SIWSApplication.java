package com.onlineinteract.application;

import com.onlineinteract.morphia.MorphiaAppDriver;
import com.onlineinteract.verticles.SocialInsuranceRestServiceVerticle;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class SIWSApplication {

	private String deploymentID;
	private Vertx vertx;

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
		System.out.println("Running Social Insurance Workflow Service (SIWS) Application.");
		new SIWSApplication();
	}
}
