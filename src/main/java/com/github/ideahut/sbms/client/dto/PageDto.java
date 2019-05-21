package com.github.ideahut.sbms.client.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class PageDto<T> implements Serializable {
	
	public static final int DEFAULT_PAGE_SIZE = 20;

	private int index;

	private int size;
	
	private int total;
	
	private long records;
	
	private List<T> data;
	
	private Map<String, Serializable> info;

	public PageDto() {
		this(1, DEFAULT_PAGE_SIZE);
	}

	public PageDto(int index) {
		this(index, DEFAULT_PAGE_SIZE);
	}

	public PageDto(int index, int size) {
		this.index = index;
		this.size = size;
	}

	public int getIndex() {
		return index;
	}

	public PageDto<T> setIndex(int index) {
		this.index = index;
		return this;
	}

	public int getSize() {
		return size;
	}

	public PageDto<T> setSize(int size) {
		this.size = size;
		return this;
	}

	public int getTotal() {
		return total;
	}

	public PageDto<T> setTotal(int total) {
		this.total = total;
		return this;
	}

	public long getRecords() {
		return records;
	}

	public PageDto<T> setRecords(long records) {
		this.records = records;
		total = ((int)(Math.ceil((float) records / size)));
		return this;
	}

	public List<T> getData() {
		return data;
	}

	public PageDto<T> setData(List<T> data) {
		this.data = data;
		return this;
	}
	
	public Map<String, Serializable> getInfo() {
		return info;
	}

	public PageDto<T> setInfo(Map<String, Serializable> info) {
		this.info = info;
		return this;
	}

	public static<T> PageDto<T> create() {
		return create(1, DEFAULT_PAGE_SIZE);
	}

	public static<T> PageDto<T> create(int index) {
		return create(index, DEFAULT_PAGE_SIZE);
	}

	public static<T> PageDto<T> create(int index, int size) {
		if (index < 1) {
			index = 1;
		}
		if (size <= 0) {
			size = DEFAULT_PAGE_SIZE;
		}
		return new PageDto<T>(index, size);
	}
	
	public static<T> PageDto<T> createEmpty() {
		PageDto<T> page = create();
		page.setData(new ArrayList<T>());
		return page;
	}

}
