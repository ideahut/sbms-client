package com.github.ideahut.sbms.client.dto.embed;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NameDescDto implements Serializable {

	private String name;
	
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}		
	
}
