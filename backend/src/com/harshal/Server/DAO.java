package com.harshal.Server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.harshal.entities.Job;
import com.harshal.entities.LocationEntities;


public class DAO{

	
	static{
			ObjectifyService.register(Job.class);
			ObjectifyService.register(LocationEntities.class);
	}
	
	public static Objectify ofy;
	public DAO(){
		ofy = ObjectifyService.ofy();
	}
	
	/*
	public Iterable<Job> searchJobs(Nationality CountryPreference,
			JobKinds jobkind,PaymentOptions payOption){
		return ofy.load().type(Job.class).filter("nationality",CountryPreference).filter("jobKind", jobkind).filter("payOptions", payOption);				
	}
	*/
	
	public List<Job> searchAll(){
		 return ofy.load().type(Job.class).list();		
	}
		
	public void SaveJob(final Job job) throws NullPointerException{
		int i=0;
		while(i<job.Locations.size()){
			job.Locations.set(i, job.Locations.get(i).toUpperCase());
			i++;
		}

		ofy.transactNew(1,new VoidWork() {

			@Override
			public void vrun() {
				job.id = ofy.factory().allocateId(Job.class).getId();
				for(String s : job.Locations){
					LocationEntities le = ofy.load().type(LocationEntities.class).id(s).now();
					if(le == null)
						throw new NullPointerException();
					
					if(le.JobsAtThisLocation == null){
						le.JobsAtThisLocation = new ArrayList<Long>();
					}
					le.JobsAtThisLocation.add(job.id);
					ofy.save().entity(le).now();				
				}
				ofy.save().entity(job).now();
			}
		});						
	}
	
	public void AddNewLocation(String name){
		LocationEntities le = new LocationEntities();
		le.name = name.toUpperCase();
		le.JobsAtThisLocation = new ArrayList<Long>();
		ofy.save().entities(le).now();
	}

	public Collection<Job> serachByIds(ArrayList<Long> list){
		return ofy.load().type(Job.class).ids(list).values();
	}
	
	public LocationEntities getLocation(String name)
	{
		name = name.toUpperCase();
		LocationEntities le = null;
		try{
			le = ofy.load().type(LocationEntities.class).id(name).now();
		}
		catch(Exception e){System.out.println("No location for:" + name);}
		return le;
	}
	
	public List<Key<LocationEntities>> getAllLocations(){
		return ofy.load().type(LocationEntities.class).keys().list();
	}
	
	
	
	
	
	
}
