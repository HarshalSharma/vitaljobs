package com.harshal.endpoints;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.harshal.Server.DAO;

public class AddNewLocation extends ServerResource{

	@Post
	public Representation AddLocation(Representation r)
	{
		Form form = new Form(r);
		
		if(!form.getFirstValue("AccessKey").equals("complete")){
			return new StringRepresentation("WRONG_KEY");
		}

		
		String name = form.getFirstValue("Name");
		
		DAO dao = new DAO();
		dao.AddNewLocation(name);
		
		return new StringRepresentation("SUCCESS");
	}
	
}
