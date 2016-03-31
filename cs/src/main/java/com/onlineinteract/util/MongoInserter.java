package com.onlineinteract.util;

import com.onlineinteract.model.Customer;
import com.onlineinteract.morphia.MorphiaAppDriver;

public class MongoInserter {

	public static void main(String[] args) {
		
		Customer customer = new Customer("125", "May", "Bell", "JG410822A");
		MorphiaAppDriver.saveData(customer);
	}
}
