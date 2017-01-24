package com.aviation.util;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataSet {
	
	@JsonProperty("Cycles")
	private List<String> cycles;

	public List<String> getCycles() {
		return cycles;
	}

	public void setCycles(List<String> cycles) {
		this.cycles = cycles;
	}

	
	
	
	
	

}
