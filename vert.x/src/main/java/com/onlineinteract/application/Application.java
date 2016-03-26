package com.onlineinteract.application;

import java.util.concurrent.CountDownLatch;

import com.onlineinteract.thread.CountDownLatchDecrementer;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class Application {

/*	Some Quotes from the Vert.x Documentation :) */
	
/*	If you have a single event loop, and you want to handle 10000 http requests 
	per second, then it’s clear that each request can’t take more than 0.1 ms to 
	process, so you can’t block for any more time than that.*/
	
/*	In a perfect world, there will be no war or hunger, all APIs will be written 
	asynchronously and bunny rabbits will skip hand-in-hand with baby lambs across 
	sunny green meadows.*/
	
/*	Fact is, many, if not most libraries, especially in the JVM ecosystem have 
	synchronous APIs and many of the methods are likely to block. A good example 
	is the JDBC API - it’s inherently synchronous, and no matter how hard it tries, 
	Vert.x cannot sprinkle magic pixie dust on it to make it asynchronous.*/
	
	public Application() {

		CountDownLatch latch = new CountDownLatch(1);
		// Call a Thread that will count down once to unblock.
		Thread t1 = new Thread(new CountDownLatchDecrementer(latch));
		t1.start();

		Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
		
/*		As discussed before, you can’t call blocking operations directly from an event 
		loop, as that would prevent it from doing any other useful work. So how can you 
		do this?

		It’s done by calling executeBlocking specifying both the blocking code to execute 
		and a result handler to be called back asynchronous when the blocking code has 
		been executed.*/
		
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
		
		// Timer will execute until the main thread has completed.
		vertx.setPeriodic(500, id -> {
			// Don't block the event loop in a handler such as this.
			// The following was a little test to see if we could block
			// the event loop (which we should never do) and of course it does.
			// The result is that the timer displays a warning once over 2000ms.
			// After 5000ms, we get exceptions. Basically prevents timer
			// from executing every 500ms. Uncomment the following to demo.
/*			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			System.out.println("Timer Fired!");
		});
		
		// TODO: Implement web server properly to test.
		vertx.createHttpServer().requestHandler(request -> {
			request.response().end("hello world");
		});
		
		// TODO: Composite Futures.
		
		// Writing Verticles:
		
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
