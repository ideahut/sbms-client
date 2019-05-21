package com.github.ideahut.sbms.client.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CodeMessageDto implements Serializable {
	
	private String code;
	
	private String object;
	
	private String field;
	
	private String message;

	public CodeMessageDto() {}
	
	public CodeMessageDto(String code, String message) {
		this(code, message, null, null);
	}
	
	public CodeMessageDto(String code, String message, String object, String field) {
		this.code 	 = code;
		this.object  = object;
		this.field 	 = field;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public CodeMessageDto setCode(String code) {
		this.code = code;
		return this;
	}

	public String getObject() {
		return object;
	}

	public CodeMessageDto setObject(String object) {
		this.object = object;
		return this;
	}

	public String getField() {
		return field;
	}

	public CodeMessageDto setField(String field) {
		this.field = field;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public CodeMessageDto setMessage(String message) {
		this.message = message;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CodeMessageDto [code=");
		builder.append(code);
		builder.append(", object=");
		builder.append(object);
		builder.append(", field=");
		builder.append(field);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
}
