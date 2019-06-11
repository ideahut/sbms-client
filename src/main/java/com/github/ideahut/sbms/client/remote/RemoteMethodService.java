package com.github.ideahut.sbms.client.remote;

import com.github.ideahut.sbms.client.exception.ResponseException;

public interface RemoteMethodService {
	
	public<R> R call(Class<R> returnType, RemoteMethodParameter parameter) throws ResponseException;

}
