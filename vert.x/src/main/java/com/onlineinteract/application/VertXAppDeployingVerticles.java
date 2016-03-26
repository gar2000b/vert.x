package com.onlineinteract.application;

import com.onlineinteract.verticles.MyVerticle;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class VertXAppDeployingVerticles {

	public VertXAppDeployingVerticles() {
		Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
		vertx.deployVerticle(new MyVerticle());
	}
	
	private String someMethod() {
		return "Processed some data independent of other vert.x Threads.";
	}
	
	public static void main(String[] args) {
		System.out.println("Running...");
		VertXAppDeployingVerticles verticles = new VertXAppDeployingVerticles();
		String result = verticles.someMethod();
		System.out.println(result);
	}
}
