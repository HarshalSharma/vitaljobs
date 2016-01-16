package com.harshal.vitaljobsearch;

import com.harshal.entities.JobsList;

import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;

public class Backend {

	String SERVER_URL = "http://vitaljobsearch.appspot.com";

	String SEARCH_URL = "/api/Search";

	public String What,Where;
	
	private JobsList setOfJobs;
	static private Backend ourInstance;
	
	private Backend(){
		setOfJobs = new JobsList();
	}
	
	public static Backend getInstance(){
		if(ourInstance == null)
		{
			ourInstance = new Backend();
		}
		return ourInstance;
	}
	
	public void SetAvailableJobs(JobsList set){
		setOfJobs = set;
	}
	
	public JobsList getSetOfJobs(){
		return setOfJobs;
	}
	
	public String getServer_Search_Url(){
		return ourInstance.SERVER_URL + SEARCH_URL;
	}
	
	public RestSearchConnector getSearchConnector(){
		ClientResource cr = new ClientResource(getServer_Search_Url());
		cr.setRequestEntityBuffering(true);
		cr.accept(MediaType.APPLICATION_ALL);
		return cr.wrap(RestSearchConnector.class);
	}
}
