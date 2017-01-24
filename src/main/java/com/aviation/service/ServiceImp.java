package com.aviation.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aviation.entity.Component;
import com.aviation.repository.ComponentRepository;

@org.springframework.stereotype.Component
public class ServiceImp {
	
	@Autowired
	private ComponentRepository compRepo;
	
	public List<Component>  getCompStatus(){
		
		
		return compRepo.findAll();
		
	
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateFailure(Long componentID, int failureProbability){
		
		compRepo.updateFailureReport(componentID,String.valueOf(failureProbability));
	}
	
	
}
