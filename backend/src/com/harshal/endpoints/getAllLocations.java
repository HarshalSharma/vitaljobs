package com.harshal.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Key;
import com.harshal.Server.DAO;
import com.harshal.entities.LocationEntities;

public class getAllLocations extends ServerResource{

	
	@Get
	public Representation get_all_locations(){
		
		DAO dao = new DAO();
		List<Key<LocationEntities>> q = dao.getAllLocations();
		
		ArrayList<String> list = new ArrayList<String>();
		for(Key<LocationEntities> key : q){
			list.add(key.getName());
		}
		return new JacksonRepresentation<ArrayList<String>>(list);
	}
	
}
