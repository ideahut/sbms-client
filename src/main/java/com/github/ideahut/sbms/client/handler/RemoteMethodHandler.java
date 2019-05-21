package com.github.ideahut.sbms.client.handler;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.github.ideahut.sbms.client.dto.RemoteMethodDto;
import com.github.ideahut.sbms.client.service.RemoteMethodService;

public class RemoteMethodHandler implements InvocationHandler {
	
	private static final boolean SINGLETON = true;
	
	private static final Map<Class<?>, Map<ClassLoader, Object>> proxiesMap = 
			Collections.synchronizedMap(new HashMap<Class<?>, Map<ClassLoader, Object>>());
	
	private static final ThreadLocal<Context> holder = new ThreadLocal<Context>();
	
	private static final InheritableThreadLocal<Context> inheritableHolder =
			new InheritableThreadLocal<Context>();
	
	
	private Class<?> serviceInterface;
	
	private RemoteMethodService remoteMethodService;
	
	
	private RemoteMethodHandler(Class<?> serviceInterface, RemoteMethodService remoteMethodService) {
		this.serviceInterface = serviceInterface;
		this.remoteMethodService = remoteMethodService;
	}
	
	public Class<?> getServiceInterface() {
		return serviceInterface;
	}

	public void setServiceInterface(Class<?> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}
	
	public RemoteMethodService getRemoteMethodService() {
		return remoteMethodService;
	}

	public void setRemoteMethodService(RemoteMethodService remoteMethodService) {
		this.remoteMethodService = remoteMethodService;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> returnType = method.getReturnType();
		if (void.class.equals(returnType) || Void.class.equals(returnType)) {
			returnType = null;
		}
		Serializable[] arguments = new Serializable[args != null ? args.length : 0];
		for (int i = 0; i < arguments.length; i++) {
			arguments[i] = (Serializable)args[i];
		}
		Context context = getContext();
		Map<String, Serializable> attributes = context != null ? context.getAttributes() : null;
		return remoteMethodService.call(
			returnType, 
			new RemoteMethodDto(
				attributes, 
				serviceInterface, 
				method.getName(), 
				method.getParameterTypes(), 
				arguments
			)
		);		
	}
	
	
	
	public static void setAttribute(String name, Serializable value, boolean inheritable) {
		Context context = getContext();
		if (context == null) {
			context = new Context(); 
			setContext(context, inheritable);
		}
		context.getAttributes().put(name, value);
	}
	
	public static void setAttribute(String name, Serializable value) {
		setAttribute(name, value, false);
	}	
	
	public static void setAttribute(Map<String, Serializable> attribute, boolean inheritable) {
		Context context = getContext();
		if (context == null) {
			context = new Context(); 
			setContext(context, inheritable);
		}
		context.setAttributes(attribute);
	}
	
	public static void setAttribute(Map<String, Serializable> attribute) {
		setAttribute(attribute, false);
	}
	
	public static Serializable getAttribute(String name) {
		Context context = getContext();
		if (context == null) {
			return null;
		}
		return context.getAttributes().get(name);
	}
	
	public static Set<String> getAttributeNames() {
		Context context = getContext();
		if (context == null) {
			return null;
		}
		Map<String, Serializable> attributes = context.getAttributes();
		return attributes.keySet();
	}
	
	public static void removeAttribute(String name) {
		Context context = getContext();
		if (context == null) {
			return;
		}
		context.getAttributes().remove(name);
	}
	
	public static void clearAttribute() {
		Context context = getContext();
		if (context == null) {
			return;
		}
		context.getAttributes().clear();
	}	
	
	public static void setSingleton(boolean singleton, boolean inheritable) {
		Context context = getContext();
		if (context == null) {
			context = new Context(); 
			setContext(context, inheritable);
		}
		context.setSingleton(singleton);
	}
	
	public static boolean isSingleton() {
		Context context = getContext();
		return context != null ? context.isSingleton() : SINGLETON;
	}
	
	
	@SuppressWarnings("unchecked")
	public static<S> S create(
		ClassLoader classLoader, 
		Class<S> serviceInterface, 
		RemoteMethodService remoteMethodService, 
		Map<String, Serializable> attribute,
		boolean inheritable
	) {
		boolean singleton = isSingleton();
		if (singleton) {
			Map<ClassLoader, Object> loaderMap = proxiesMap.get(serviceInterface);
			if (loaderMap == null) {
				loaderMap = new HashMap<ClassLoader, Object>();
				proxiesMap.put(serviceInterface, loaderMap);
			}
			Object proxy = loaderMap.get(classLoader);
			if (proxy != null) {
				return (S)proxy;
			}
		}
		Object proxy = Proxy.newProxyInstance(
			classLoader, 
			new Class[] { serviceInterface }, 
			new RemoteMethodHandler(serviceInterface, remoteMethodService)
		);
		if (singleton) {
			proxiesMap.get(serviceInterface).put(classLoader, proxy);
		}
		if (attribute != null) {
			setAttribute(attribute, inheritable);			
		}
		return (S)proxy;
	}
	
	public static<S> S create(
		ClassLoader classLoader, 
		Class<S> serviceInterface, 
		RemoteMethodService remoteMethodService
	) {
		return create(classLoader, serviceInterface, remoteMethodService, null, false);
	}
	
	public static<S> S create(
		Class<S> serviceInterface, 
		RemoteMethodService remoteMethodService, 
		Map<String, Serializable> attributes,
		boolean inheritable
	) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader(); 
		return create(classLoader, serviceInterface, remoteMethodService, attributes, inheritable);
	}
	
	public static<S> S create(
		Class<S> serviceInterface, 
		RemoteMethodService remoteMethodService
	) {
		return create(serviceInterface, remoteMethodService, null, false);
	}

	
	private static void removeContext() {
		holder.remove();
		inheritableHolder.remove();
	}
	
	private static void setContext(Context context, boolean inheritable) {
		if (context == null) {
			removeContext();
		} else {
			if (inheritable) {
				inheritableHolder.set(context);
				holder.remove();
			} else {
				holder.set(context);
				inheritableHolder.remove();
			}
		}
	}
	
	private static Context getContext() {
		Context context = holder.get();
		if (context == null) {
			context = inheritableHolder.get();
		}
		return context;
	}
	
	private static class Context {
		private Map<String, Serializable> attributes = new HashMap<String, Serializable>();
		private boolean singleton = SINGLETON;
		public Map<String, Serializable> getAttributes() {
			return attributes;
		}
		public void setAttributes(Map<String, Serializable> attributes) {
			this.attributes = attributes != null ? attributes : new HashMap<String, Serializable>();
		}
		public boolean isSingleton() {
			return singleton;
		}
		public void setSingleton(boolean singleton) {
			this.singleton = singleton;
		}	
	}
	
}
