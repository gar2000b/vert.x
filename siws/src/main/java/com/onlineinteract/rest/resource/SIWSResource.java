package com.onlineinteract.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1")
public class SIWSResource {
	
	@POST
	@Path("/{requestNo}/{customerId}")
	@Consumes(MediaType.TEXT_PLAIN)
	// curl -i -X POST http://192.168.0.23:8080/siws/v1/123/456
	public Response sendSINRequest(@PathParam("requestNo") String requestNo, @PathParam("customerId") String customerId) {
		System.out.println("* sinRequest requestNo: " + requestNo + ", customerId: " + customerId);
		return Response.status(200).entity("SIN Request Received OK and forwarded to SIVS for further processing").build();
	}
}
