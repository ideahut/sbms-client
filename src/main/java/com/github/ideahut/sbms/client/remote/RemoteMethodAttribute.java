package com.github.ideahut.sbms.client.remote;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class RemoteMethodAttribute {
	
	private static final ThreadLocal<Map<String, Serializable>> holder = new ThreadLocal<Map<String, Serializable>>();	

	public static void remove() {
		holder.remove();
	}
	
	public static void set(Map<String, Serializable> attributes) {
		if (attributes == null) {
			holder.remove();
		} else {
			holder.set(attributes);
		}
	}
	
	public static void set(String name, String value) {
		Map<String, Serializable> attributes = holder.get();
		if (attributes == null) {
			holder.set(new HashMap<String, Serializable>());
			attributes = holder.get();
		}
		attributes.put(name, value);
	}
	
	public static Map<String, Serializable> get() {
		return holder.get();
	}
}
