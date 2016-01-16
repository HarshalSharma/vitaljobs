package com.harshal.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Job implements Serializable{

	/**
	 * 	Version 1
	 */
	private static final long serialVersionUID = 8654059907056702986L;

	@Id
	public Long id;
	
	
	public String JobTitle;
	public String JobDescription;
	
	public JobKinds jobKind;
	
	@Index
	public PaymentOptions payOptions;
	
	public ArrayList<String> Locations;
}
