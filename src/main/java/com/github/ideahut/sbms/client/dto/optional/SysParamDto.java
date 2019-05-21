package com.github.ideahut.sbms.client.dto.optional;

import com.github.ideahut.sbms.client.dto.base.DtoLongIdTimeVersion;

@SuppressWarnings("serial")
public class SysParamDto extends DtoLongIdTimeVersion {
	
	private Integer sys;
	
	private Integer param;
	
	private String value;
	
	private byte[] bytes;
	
	private String desc;
	

	public Integer getSys() {
		return sys;
	}

	public void setSys(Integer sys) {
		this.sys = sys;
	}

	public Integer getParam() {
		return param;
	}

	public void setParam(Integer param) {
		this.param = param;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
