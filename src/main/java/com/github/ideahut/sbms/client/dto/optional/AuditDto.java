package com.github.ideahut.sbms.client.dto.optional;

import java.util.Date;

import com.github.ideahut.sbms.client.dto.base.DtoStringId;

@SuppressWarnings("serial")
public class AuditDto extends DtoStringId {

	private String auditor;
	
	private String action;
	
	private String classname;
	
	private String content;
	
	private byte[] bytes;
	
	private Date entry;

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public Date getEntry() {
		return entry;
	}

	public void setEntry(Date entry) {
		this.entry = entry;
	}		

}
