package com.onlineinteract.rest.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.onlineinteract.application.CSApplication;
import com.onlineinteract.model.Customer;
import com.onlineinteract.model.Simple;
import com.onlineinteract.model.URL;

@Path("v1")
public class CSResource {

    // get example returning JSON
    // curl: curl -i http://localhost:8080/cs/v1/getCustomer/123
	@GET
	@Path("/getCustomer/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("customerId") String customerId) {
		System.out.println("* Incoming request with customer id of: " + customerId);
		Customer customer = fetchCustomer("123");
		return customer;
		// return Response.ok("{\"blah\":\"blah\"}", MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/getSimple")
	@Produces(MediaType.APPLICATION_JSON)
	public Simple getSimple() {
		Simple simple = new Simple();
		simple.setItem("blah");
		return simple;
	}
	
	@GET
	@Path("/getURLs")
	@Produces(MediaType.APPLICATION_JSON)
	public List<URL> getURLsInJSON() {

		List<URL> urlList = new ArrayList<URL>();

		URL url1 = new URL();
		url1.setUrl("http://" + "whatever" +":"+ "whateverport" + "/services/swagger.json");
		
		url1.setNameOfTheService("EventLogger");
		urlList.add(url1);

		URL url2 = new URL();
		url2.setUrl("http://" + "whatever" + ":8080/api/client-import-export/services/swagger.json");
		url2.setNameOfTheService("ClientImportExportService");
		urlList.add(url2);

		System.out.println("* Got URLS.");

		return urlList;
	}

	private Customer fetchCustomer(String customerId) {
		
		Customer customer = new Customer("123", "James", "May", "JG410821A", CSApplication.APP_ID.toString());
		
		return customer;
	}
}
