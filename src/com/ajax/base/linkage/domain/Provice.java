package com.ajax.base.linkage.domain;

import com.ajax.base.util.IngoreJson;


public class Provice implements BasicDomain{
	private Long id;
	private String name;
	private String pingyinName;
	private int sequence;
	private String initialPingYin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@IngoreJson
	public String getPingyinName() {
		return pingyinName;
	}

	public void setPingyinName(String pingyinName) {
		this.pingyinName = pingyinName;
	}

	@IngoreJson
	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@IngoreJson
	public String getInitialPingYin() {
		return initialPingYin;
	}

	public void setInitialPingYin(String initialPingYin) {
		this.initialPingYin = initialPingYin;
	}

	@Override
	public String toString() {
		return "Provice [id=" + id + ", name=" + name + ", pingyinName="
				+ pingyinName + ", sequence=" + sequence + ", initialPingYin="
				+ initialPingYin + "]";
	}

}
