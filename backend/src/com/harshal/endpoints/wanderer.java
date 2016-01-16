package com.harshal.endpoints;

import org.restlet.data.Reference;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;


public class wanderer extends ServerResource{

	
	public Representation get(){
		try{
//		URL url = new URL("");
		Reference ref = getRequest().getHostRef().clone();
		redirectPermanent(ref);
		}
		catch(Exception e){}
		return new StringRepresentation("");
	}
}
