package com.github.ideahut.sbms.client.dto.embed;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class PersonDto implements Serializable {
	
	private String name;
	
	private String birthplace;
	
	private Date birthdate;
	
	private String gender;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
