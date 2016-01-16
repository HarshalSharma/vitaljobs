package com.harshal.Server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.harshal.endpoints.AddJobEndPoint;
import com.harshal.endpoints.AddNewLocation;
import com.harshal.endpoints.SearchByBrowser;
import com.harshal.endpoints.SearchEndPoint;
import com.harshal.endpoints.getAllLocations;
import com.harshal.endpoints.wanderer;

public class RestletDispatch extends Application{

	
	@Override
	public synchronized Restlet createInboundRoot() {
	
		Router router = new Router(getContext());
		
		String ApiUrl = "/api";
		
		//Configured with the app
		router.attach(ApiUrl + "/Search",SearchEndPoint.class);
		
		
		router.attach(ApiUrl + "/addJob",AddJobEndPoint.class);
		router.attach(ApiUrl + "/addLocation",AddNewLocation.class);
		router.attach(ApiUrl + "/locations",getAllLocations.class);
		router.attach(ApiUrl + "/WebSearch",SearchByBrowser.class);
		router.attachDefault(wanderer.class);
		
		return router;
	
	}

}
