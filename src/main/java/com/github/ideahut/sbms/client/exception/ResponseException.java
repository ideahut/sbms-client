package com.github.ideahut.sbms.client.exception;

import com.github.ideahut.sbms.client.dto.ResponseDto;

@SuppressWarnings("serial")
public class ResponseException extends Exception {

	private final ResponseDto response;
	
	public ResponseException(ResponseDto response) {
		super(response != null ? response.toString() : null);
		this.response = response;
	}

	public ResponseDto getResponse() {
		return response;
	}	
	
}
