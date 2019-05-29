package com.github.ideahut.sbms.client.dto.optional;

import java.util.Date;

import com.github.ideahut.sbms.client.dto.base.DtoStringId;

@SuppressWarnings("serial")
public class AuditDto extends DtoStringId {

	private String auditorId;
	
	private String auditorName;
	
	private String action;
	
	private String info;
	
	private String type;	
	
	private String content;
	
	private byte[] bytes;
	
	private Date entry;

	public String getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
