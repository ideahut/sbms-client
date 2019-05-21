package com.github.ideahut.sbms.client.service;

import com.github.ideahut.sbms.client.dto.RemoteMethodDto;
import com.github.ideahut.sbms.client.exception.ResponseException;

public interface RemoteMethodService {
	
	public<R> R call(Class<R> returnType, RemoteMethodDto dto) throws ResponseException;

}
