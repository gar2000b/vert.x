package com.onlineinteract.verticles;

import java.util.concurrent.CountDownLatch;

import com.onlineinteract.thread.CountDownLatchDecrementer;

import io.vertx.core.AbstractVerticle;

public class MyVerticle extends AbstractVerticle {

	// Called when verticle is deployed
	public void start() {
		CountDownLatch latch = new CountDownLatch(1);
		// Call a Thread that will count down once to unblock.
		Thread t1 = new Thread(new CountDownLatchDecrementer(latch));
		t1.start();
		
		vertx.executeBlocking(future -> {
			// Call some blocking API that takes a set amount of time(our case 5 secs).
				try {
					latch.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
				future.complete("Pass, countdown is now 0.");
			}, false, res -> {
				System.out.println("The result is: " + res.result());
				vertx.close();
			});
	}

	// Optional - called when verticle is undeployed
	public void stop() {
	}

}