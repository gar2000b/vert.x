package com.onlineinteract.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

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
	
}
