package com.onlineinteract.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.onlineinteract.model.Customer;
import com.onlineinteract.morphia.MorphiaAppDriver;

@Path("v1")
public class CSResource {

    // get example returning JSON
    // curl: curl -i http://localhost:8080/cs/v1/getCustomer/123
	@GET
	@Path("/getCustomer/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("customerId") String customerId) {
		System.out.println("* Incoming request with customer id of: " + customerId);
		return fetchCustomer(customerId);
	}

	private Customer fetchCustomer(String customerId) {
		return MorphiaAppDriver.findCustomer(customerId);
	}
}
