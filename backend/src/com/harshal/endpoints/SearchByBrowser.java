package com.harshal.endpoints;

import java.util.HashSet;

import org.restlet.data.Form;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.harshal.endpoints.SearchEndPoint.JobsList;
import com.harshal.entities.QueryRestPassable;

public class SearchByBrowser extends ServerResource{

	@Get("json")
	public Representation search(){

		Form form = getQuery();
		String what = 	form.getFirstValue("what");
		String where = 	form.getFirstValue("where");
		
		QueryRestPassable obj = new QueryRestPassable();
		obj.What = what;
		obj.Where = new HashSet<String>();
		obj.Where.add(where);
		
		SearchEndPoint endpoint = new SearchEndPoint();
		JobsList list = endpoint.SearchJobs(obj);
		
		return new JacksonRepresentation<JobsList>(list);
	}	
}
