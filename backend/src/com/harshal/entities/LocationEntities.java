package com.harshal.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class LocationEntities implements Serializable{

	private static final long serialVersionUID = -6265160745225240966L;

	
	@Id
	public String name;
	
	public ArrayList<Long> JobsAtThisLocation;

	
}
