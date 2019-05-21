package com.github.ideahut.sbms.client.dto.base;

@SuppressWarnings("serial")
public abstract class DtoStringIdTime extends DtoTime {
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
