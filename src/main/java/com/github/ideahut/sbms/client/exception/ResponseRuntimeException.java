package com.github.ideahut.sbms.client.exception;

import com.github.ideahut.sbms.client.dto.ResponseDto;

@SuppressWarnings("serial")
public class ResponseRuntimeException extends RuntimeException {

	private final ResponseDto response;
	
	public ResponseRuntimeException(ResponseDto response) {
		super(response != null ? response.toString() : null);
		this.response = response;
	}

	public ResponseDto getResponse() {
		return response;
	}
	
}
