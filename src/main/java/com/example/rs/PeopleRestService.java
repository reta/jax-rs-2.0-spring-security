package com.example.rs;

import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path( "/people" ) 
public class PeopleRestService {
	@Produces( { "application/json" } )
	@GET
	public JsonArray getPeople() {
		return Json.createArrayBuilder()
		    .add( Json.createObjectBuilder()
		      .add( "firstName", "Tom" )
		      .add( "lastName", "Tommyknocker" )
		      .add( "email", "a@b.com" ) )
		    .build();
	}
}
