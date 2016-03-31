package com.onlineinteract.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;
import com.onlineinteract.model.Customer;

public class MorphiaAppDriver {
	
	private static Datastore datastore;
	private static final Morphia morphia = new Morphia();
	private static final MorphiaAppDriver instance = new MorphiaAppDriver();
	
	protected MorphiaAppDriver(){
		System.out.println("*** MorphiaAppDriver Constructor: Do I get called?");
		
		morphia.mapPackage("com.onlineinteract.model");
		datastore = morphia.createDatastore(new MongoClient(), "mydb");
		datastore.ensureIndexes();
	}
	
	public static MorphiaAppDriver getInstance(){
		System.out.println("* Returning instance");
		return instance;
	}
	
	public static void saveData(Object morphiaDataObject) {
		datastore.save(morphiaDataObject);
	}
	
	public static Customer findCustomer(String customerId) {
		Query<Customer> query = datastore.createQuery(Customer.class);
		Query<Customer> customer = query.filter("customerId ==", customerId);
		return customer.get();
	}
	
}
