package com.aviation.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aviation.entity.Component;

public interface ComponentRepository extends JpaRepository<Component, Serializable>{
	
	@Modifying 
	@Query("UPDATE Component comp SET comp.failureProbability = :failureProbability where comp.componentID= :componentID")
	public void updateFailureReport(@Param("componentID")Long componentID, @Param("failureProbability")String failureProbability);
}
