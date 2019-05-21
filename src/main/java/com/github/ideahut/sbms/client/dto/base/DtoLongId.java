package com.github.ideahut.sbms.client.dto.base;

@SuppressWarnings("serial")
public abstract class DtoLongId extends DtoBase {
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
