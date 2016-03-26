package com.onlineinteract.application;

import com.onlineinteract.verticles.MyVerticle;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class VertXAppDeployingVerticles {

	private String deploymentID;
	private Vertx vertx;

	public VertXAppDeployingVerticles() {
		vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));

		// deploying a standard verticle.
		vertx.deployVerticle(new MyVerticle(), res -> {
			if (res.succeeded()) {
				deploymentID = res.result();
				System.out.println("Deployment id is: " + res.result());
			} else {
				System.out.println("Deployment failed!");
			}
		});

		// can also deploy verticle using it's name. In java this is the fqn of
		// the class.
		// vertx.deployVerticle("com.onlineinteract.verticles.MyVerticle");
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

	private String someMethod() {
		return "Processed some data independent of other vert.x Threads.";
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Running...");
		VertXAppDeployingVerticles vertXAppDeployingVerticles = new VertXAppDeployingVerticles();
		String result = vertXAppDeployingVerticles.someMethod();
		System.out.println(result);
		
		// The following allows us to undeploy before our verticle completes and undeploys first.
		// albeit, our verticle kicks off a blocking future which will complete OK even if we
		// undeploy the vertcle beforehand.
		Thread.sleep(3000);
		vertXAppDeployingVerticles.undeployVerticles();
		Thread.sleep(1000);
	}
}
