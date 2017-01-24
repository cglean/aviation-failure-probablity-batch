package com.aviation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "component")
public class Component {
	
	@Id
	@Column(name = "componentID")
	private Long componentID;
	
	@Column(name = "mnfg_date")
	private Date mnfgDate;
	
	
	@Column(name = "cycles")
	private String cycles;
	
	
	@Column(name = "failure_probability")
	private String failureProbability;

	public Long getComponentID() {
		return componentID;
	}


	public void setComponentID(Long componentID) {
		this.componentID = componentID;
	}


	public Date getMnfgDate() {
		return mnfgDate;
	}


	public void setMnfgDate(Date mnfgDate) {
		this.mnfgDate = mnfgDate;
	}


	public String getCycles() {
		return cycles;
	}


	public void setCycles(String cycles) {
		this.cycles = cycles;
	}


	
	
	

}
