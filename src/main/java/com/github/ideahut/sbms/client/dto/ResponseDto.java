package com.github.ideahut.sbms.client.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class ResponseDto implements Serializable {
	
	public enum Status implements Serializable {
		SUCCESS,
		INPROGRESS,
		FAILED,
		ERROR
	}
	
	public static ResponseDto SUCCESS(Object data) {
		return new ResponseDto(Status.SUCCESS, data, null);
	}
	
	public static ResponseDto SUCCESS() {
		return new ResponseDto(Status.SUCCESS, null, null);
	}
	
	public static ResponseDto STATUS(Status status, Object data) {
		return new ResponseDto(status, data, null);
	}
	
	public static ResponseDto ERROR(List<CodeMessageDto> error) {
		return new ResponseDto(Status.ERROR, null, error);
	}
	
	public static ResponseDto ERROR() {
		return new ResponseDto(Status.ERROR, null, null);
	}
	
	public static ResponseDto ERROR(CodeMessageDto error) {
		List<CodeMessageDto> list = new ArrayList<CodeMessageDto>();
		list.add(error);
		return ERROR(list);
	}
	
	public static ResponseDto ERROR(String code, String message) {
		return ERROR(new CodeMessageDto(code, message));
	}
	
	public static ResponseDto ERROR(String code) {
		return ERROR(new CodeMessageDto(code, null));
	}
	
	private Status status;
	
	private List<CodeMessageDto> error;
	
	private Object data;
	
	private Map<String, String> info;

	private ResponseDto(Status status, Object data, List<CodeMessageDto> error) {
		this.status = status;
		this.data   = data;
		this.error  = error;
	}	

	public List<CodeMessageDto> getError() {
		return error;
	}

	public Status getStatus() {
		return status;
	}

	public Object getData() {
		return data;
	}
	
	public ResponseDto setInfo(String key, String value) {
		if (info == null) {
			info = new HashMap<String, String>();
		}
		info.put(key, value);
		return this;
	}
	
	public Map<String, String> getInfo() {
		return info;
	}

	public boolean hasError() {
		return error != null && !error.isEmpty();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseDto [status=");
		builder.append(status);
		builder.append(", error=");
		builder.append(error);
		builder.append(", data=");
		builder.append(data);
		builder.append(", info=");
		builder.append(info);
		builder.append("]");
		return builder.toString();
	}
	
}
