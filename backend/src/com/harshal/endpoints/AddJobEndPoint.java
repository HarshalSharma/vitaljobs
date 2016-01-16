package com.harshal.endpoints;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.harshal.Server.DAO;
import com.harshal.entities.Job;
import com.harshal.entities.JobKinds;
import com.harshal.entities.PaymentOptions;

public class AddJobEndPoint extends ServerResource{

	
	@Post
	public Representation AddJob(Representation e){

		
		final Form form = new Form(e);
			
		
		if(!form.getFirstValue("AccessKey").equals("complete")){
			return new StringRepresentation("WRONG_KEY");
		}
		
		
		Job job = new Job();
		job.JobTitle = form.getFirstValue("JobTitle");
		job.JobDescription = form.getFirstValue("JobDescription");
		Set<String> set = new HashSet<String>();
		
		for(String s : form.getValuesArray("location")){
			set.add(s);
		}
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(set);
		job.Locations = list;
		
//		job.jobKind = JobKinds.valueOf(form.getFirstValue("jobKind"));
		job.payOptions = PaymentOptions.valueOf(form.getFirstValue("payOptions"));
		
		DAO dao = new DAO();
		
		try{
			dao.SaveJob(job);
			return new StringRepresentation("SUCCESS");
		}
		catch(NullPointerException exp)
		{
			return new StringRepresentation("NO_SUCH_LOCATION");			
		}
	}
	
	@Override
	protected void doError(Status errorStatus) {
		super.doError(errorStatus);
	}
	
}
