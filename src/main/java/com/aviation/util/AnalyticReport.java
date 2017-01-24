package com.aviation.util;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.redis.connection.convert.StringToDataTypeConverter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)

public class AnalyticReport {
 
	@JsonRawValue
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	

	

		

	
}
