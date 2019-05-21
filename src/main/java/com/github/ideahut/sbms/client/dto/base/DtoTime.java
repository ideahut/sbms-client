package com.github.ideahut.sbms.client.dto.base;


import java.util.Date;

@SuppressWarnings("serial")
public abstract class DtoTime extends DtoBase {
	
	private Date createdAt;
	
	private Date updatedAt;
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
