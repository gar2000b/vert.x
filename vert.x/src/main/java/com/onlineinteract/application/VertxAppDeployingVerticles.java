package com.onlineinteract.application;

import com.onlineinteract.verticles.MyVerticle;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class VertxAppDeployingVerticles {

	public VertxAppDeployingVerticles() {
		Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
		vertx.deployVerticle(new MyVerticle());
	}
	
	private String someMethod() {
		return "Processed some data independent of other vert.x Threads.";
	}
	
	public static void main(String[] args) {
		System.out.println("Running...");
		VertxAppDeployingVerticles verticles = new VertxAppDeployingVerticles();
		String result = verticles.someMethod();
		System.out.println(result);
	}
}
