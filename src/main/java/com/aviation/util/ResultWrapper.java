package com.aviation.util;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultWrapper {

	
	        private List<String> result;

			public List<String> getResult() {
				return result;
			}

			public void setResult(List<String> result) {
				this.result = result;
			}

}	        
	

