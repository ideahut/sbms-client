package com.github.ideahut.sbms.client.dto;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
public class RemoteMethodDto implements Serializable {
	
	private Map<String, Serializable> attributes;
	
	private Class<?> serviceClass;
	
	private String methodName;
	
	private Class<?>[] parameterTypes;
	
	private Serializable[] arguments;
	
	public RemoteMethodDto() {}
	
	public RemoteMethodDto(Map<String, Serializable> attributes, Class<?> serviceClass, String methodName, Class<?>[] parameterTypes, Serializable...arguments) {
		this.attributes = attributes;
		this.serviceClass = serviceClass;
		this.methodName = methodName;
		this.parameterTypes = parameterTypes;
		this.arguments = arguments;
	}
	
	public RemoteMethodDto(Class<?> serviceClass, String methodName, Class<?>[] parameterTypes, Serializable...arguments) {
		this(null, serviceClass, methodName, parameterTypes, arguments);
	}
	
	public RemoteMethodDto(Map<String, Serializable> attributes, Class<?> serviceClass, String methodName, Serializable...arguments) {
		this(attributes, serviceClass, methodName, null, arguments);
	}
	
	public RemoteMethodDto(Class<?> serviceClass, String methodName, Serializable...arguments) {
		this(null, serviceClass, methodName, null, arguments);
	}
	
	public Map<String, Serializable> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Serializable> attributes) {
		this.attributes = attributes;
	}

	public Class<?> getServiceClass() {
		return serviceClass;
	}

	public void setServiceClass(Class<?> serviceClass) {
		this.serviceClass = serviceClass;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public Serializable[] getArguments() {
		if (arguments == null) {
			arguments = new Serializable[0];
		}
		return arguments;
	}

	public void setArguments(Serializable[] arguments) {
		this.arguments = arguments;
	}
	
}
