package com.harshal.entities;

import java.io.Serializable;
import java.util.ArrayList;



public class JobRestPassable implements Serializable{
	
	private static final long serialVersionUID = -2587372576973609342L;

	public Long id;
	
	
	public String JobTitle;
	public String JobDescription;
	
	public ArrayList<String> Locations;
	
	public JobKinds jobKind;
	
	public PaymentOptions payOptions;
	
	public int KeywordMatches;
	
	@Override
	public String toString() {
		return JobTitle;
	}
}
