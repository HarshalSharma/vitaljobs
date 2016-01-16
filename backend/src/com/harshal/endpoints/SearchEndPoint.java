package com.harshal.endpoints;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.harshal.Server.DAO;
import com.harshal.entities.Job;
import com.harshal.entities.JobRestPassable;
import com.harshal.entities.LocationEntities;
import com.harshal.entities.QueryRestPassable;

public class SearchEndPoint extends ServerResource {

	
	@Put
	public JobsList SearchJobs(QueryRestPassable qObject) {
		JobsList list = new JobsList();
		
		Set<String> locations = qObject.Where;
		
		
		DAO dao = new DAO();
		
		Set<Job> jobs = new HashSet<Job>();
		
		if(locations == null)
		{
			jobs.addAll(dao.searchAll());
		}
		else{
			for(String location: locations){
				LocationEntities le = null;				
				try{
					le = dao.getLocation(location.trim());
				}catch(Exception exp){}

				if(le == null)
				{
					list.Message = "NO_SUCH_LOCATION";
					return list;
				}
				try{
					jobs.addAll(dao.serachByIds(le.JobsAtThisLocation));
				}
				catch(Exception e){System.out.println("NO JOBS FOUND FOR "+ location); list.Message = "NO Results found for " + location ;}
			}
			
		}
		
		if(qObject.What.trim().length() == 0){
			for(Job j : jobs){
				JobRestPassable r = new JobRestPassable();
				r.id = j.id;
				r.JobTitle = j.JobTitle;
				r.JobDescription = j.JobDescription;
				r.jobKind = j.jobKind;
				r.Locations = j.Locations;
				r.payOptions = j.payOptions;
				list.add(r);	
			}
			return list;
		}
		
		for(Job j : jobs){
			String title = j.JobTitle.toUpperCase();
			String description = j.JobDescription.toUpperCase();
			
			for(String i : qObject.What.split("\\s+")){
				boolean check = false;
				if(title.toUpperCase().matches("(.*)"+ i.toUpperCase() +"(.*)")){
						check = true;
				}
				else if(description.toUpperCase().matches("(.*)"+ i.toUpperCase() +"(.*)"))
				{
					check = true;
				}
				
				if(check){
					JobRestPassable r = new JobRestPassable();
					r.id = j.id;
					r.JobTitle = j.JobTitle;
					r.JobDescription = j.JobDescription;
					r.Locations = j.Locations;
					r.jobKind = j.jobKind;
					r.payOptions = j.payOptions;
					list.add(r);
				}
			}
			
		}
		list.Message = "SUCCESS";
		return list;
	}
	
	@Get
	public JobsList AllJobs() {
		JobsList list = new JobsList();
		
		DAO dao = new DAO();
		Iterable<Job> jobs = dao.searchAll();
		
		for(Job j : jobs){
			JobRestPassable r = new JobRestPassable();
			r.id = j.id;
			r.JobTitle = j.JobTitle;
			r.JobDescription = j.JobDescription;
			r.jobKind = j.jobKind;
			r.Locations = j.Locations;
			r.payOptions = j.payOptions;
			list.add(r);
		}
		list.Message = "SUCCESS";
		return list;
	}
	
	@Override
	protected void doCatch(Throwable arg0) {
		super.doCatch(arg0);
		arg0.printStackTrace();
	}
	
	
	
	class JobsList extends ArrayList<JobRestPassable> {

		public String Message = "";
		
		private static final long serialVersionUID = -7451597633304641045L;

	}
	
}
