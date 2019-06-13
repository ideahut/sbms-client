package com.github.ideahut.sbms.client.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class ServiceProxyWrapper<SERVICE> implements InvocationHandler {

	private final SERVICE service;
	
	private Map<String, String> headers;
	
	private ServiceProxyWrapper(SERVICE service) {
		this.service = service;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Map<String, String> headers = ServiceProxyHeader.get();
		if (headers == null) {
			ServiceProxyHeader.set(this.headers);
		} else {
			if (this.headers != null) {
				headers.putAll(this.headers);
			}
		}
		return method.invoke(service, args);
	}
	
	@SuppressWarnings("unchecked")
	public static<SERVICE> SERVICE withHeaders(
		ClassLoader classLoader,
		Class<SERVICE> serviceInterface,
		SERVICE service, 
		Map<String, String> headers
	) {
		ServiceProxyWrapper<SERVICE> wrapper = new ServiceProxyWrapper<SERVICE>(service);
		wrapper.headers = headers;
		return (SERVICE)Proxy.newProxyInstance(
			classLoader, 
			new Class[] { serviceInterface },
			wrapper
		);
	}

	public static<SERVICE> SERVICE withHeaders(
		Class<SERVICE> serviceInterface,
		SERVICE service, 
		Map<String, String> headers
	) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return withHeaders(classLoader, serviceInterface, service, headers);
	}
	
}
