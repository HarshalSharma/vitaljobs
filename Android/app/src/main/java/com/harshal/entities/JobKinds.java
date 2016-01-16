package com.harshal.entities;

public enum JobKinds {

	ComputerScienceAndEngineering("Computer Science Jobs"),
	ComputersAndCommunicationEngineering("Telecommunication and Networking Jobs"),
	MechanicalEngineering("Mechanical Industries and Factories"),
	CivilEngineering("Civil Services"),
	ElectricalEngineering("Electrical Engineering and Manufacturing"),
	ElectronicsEngineering("Electronic Engineering jobs"),
	ElectronicsAndCommunicationEngineering("Part time or Work from home jobs"),
	Architecture("Architecture and Design"),
	LAW("LAW Practice and Studies"),
	MBA("Bussiness and Management"),
	HotelManagement("Catering, Cooking or other Hotel Jobs");
	
	private String name;
	
	JobKinds(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
