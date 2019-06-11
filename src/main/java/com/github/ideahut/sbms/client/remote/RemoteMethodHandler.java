package com.github.ideahut.sbms.client.remote;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class RemoteMethodHandler implements InvocationHandler {
	
	private final Class<?> serviceInterface;
	
	private final RemoteMethodService remoteMethodService;
	
	
	private RemoteMethodHandler(Class<?> serviceInterface, RemoteMethodService remoteMethodService) {
		this.serviceInterface = serviceInterface;
		this.remoteMethodService = remoteMethodService;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			Class<?> returnType = method.getReturnType();
			if (void.class.equals(returnType) || Void.class.equals(returnType)) {
				returnType = null;
			}
			Serializable[] arguments = new Serializable[args != null ? args.length : 0];
			for (int i = 0; i < arguments.length; i++) {
				arguments[i] = (Serializable)args[i];
			}
			Map<String, Serializable> attributes = RemoteMethodAttribute.get();		
			return remoteMethodService.call(
				returnType, 
				new RemoteMethodParameter(
					attributes, 
					serviceInterface, 
					method.getName(), 
					method.getParameterTypes(), 
					arguments
				)
			);
		} catch (Throwable e) {
			throw e;
		} finally {
			RemoteMethodAttribute.remove();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static<S> S create(
		ClassLoader classLoader, 
		Class<S> serviceInterface, 
		RemoteMethodService remoteMethodService
	) {
		Object proxy = Proxy.newProxyInstance(
			classLoader, 
			new Class[] { serviceInterface }, 
			new RemoteMethodHandler(serviceInterface, remoteMethodService)
		);
		return (S)proxy;
	}
	
	public static<S> S create(
		Class<S> serviceInterface, 
		RemoteMethodService remoteMethodService
	) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return create(classLoader, serviceInterface, remoteMethodService);
	}	
}
