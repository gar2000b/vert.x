package com.onlineinteract.rest.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.onlineinteract.model.Customer;
import com.onlineinteract.model.SINRequestResponse;
import com.onlineinteract.model.Simple;

@Path("v1")
public class CatchAllResource {

	@Context
	UriInfo uriInfo;

	/* This is our catch all method for GET */

	@GET
	@Path("/{seg: .*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Simple getSimple() {

		System.out.println(uriInfo.getPath());

		List<PathSegment> lps = uriInfo.getPathSegments();

		for (PathSegment pathSegment : lps) {
			System.out.println(pathSegment.getPath());
		}

		Simple simple = new Simple();
		simple.setItem("blah");
		return simple;
	}

	@POST
	@Path("/{seg: .*}")
	@Consumes(MediaType.APPLICATION_JSON)
	// curl -H "Content-Type: application/json" -X POST -d '{"username":"xyz","password":"xyz"}' http://localhost:8080/ca/v1/insert/customer
	public Response insertCustomer(String customerJSON) {
		
		System.out.println(customerJSON);
		System.out.println(uriInfo.getPath());
		
		return Response.status(200).entity("POST received OK").build();
	}
}
