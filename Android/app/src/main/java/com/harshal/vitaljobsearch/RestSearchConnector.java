package com.harshal.vitaljobsearch;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import com.harshal.entities.JobsList;
import com.harshal.entities.QueryRestPassable;


public interface RestSearchConnector {

	
	@Put
	public JobsList SearchJobs(QueryRestPassable obj);
	
	@Get
	public JobsList AllJobs();

}
