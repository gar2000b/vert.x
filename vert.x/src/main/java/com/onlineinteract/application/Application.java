package com.onlineinteract.application;

import io.vertx.core.Vertx;

import java.util.concurrent.CountDownLatch;

import com.onlineinteract.thread.CountDownLatchDecrementer;

public class Application {

	public Application() {

		CountDownLatch latch = new CountDownLatch(1);
		// Call a Thread that will count down once to unblock.
		Thread t1 = new Thread(new CountDownLatchDecrementer(latch));
		t1.start();

		Vertx vertx = Vertx.vertx();
		vertx.executeBlocking(future -> {
			// Call some blocking API that takes a set amount of time(our case 5 secs).
				try {
					latch.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
				future.complete("Pass, countdown is now 0.");
			}, res -> {
				System.out.println("The result is: " + res.result());
				vertx.close();
			});
	}

	private String someMethod() {
		return "Processed some data independent of other vert.x Threads.";
	}

	public static void main(String[] args) {
		System.out.println("Running...");
		Application application = new Application();
		String result = application.someMethod();
		System.out.println(result);
	}
}
