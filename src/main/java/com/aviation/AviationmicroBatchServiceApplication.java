package com.aviation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.aviation.conn.ComponentStatus;
import com.aviation.entity.Component;
import com.aviation.service.ServiceImp;
import com.aviation.util.AccessToken;
import com.aviation.util.AnalyticReport;
import com.aviation.util.Constants;
import com.aviation.util.Data;
import com.aviation.util.DataSet;
import com.aviation.util.ResultWrapper;
import com.aviation.util.Utility;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class AviationmicroBatchServiceApplication {
	
	@Autowired
	ServiceImp serviceImp;
	
	@Autowired
	ApplicationContext applicationContext;
	public static void main(String[] args) {
		
		SpringApplication.run(AviationmicroBatchServiceApplication.class, args);
		
	}
	
	
	@Bean
	public CommandLineRunner commandLineRunne(ApplicationContext applicationContext){
		return args ->{
			
			
			

           Map<Long, Integer> compFailurData = analyticResult();
           
           Iterator it = compFailurData.entrySet().iterator();
			
			    while (it.hasNext()) {
			    	
			        Map.Entry<Long,Integer> pair = (Map.Entry<Long,Integer>)it.next();
			        serviceImp.updateFailure( pair.getKey(),  pair.getValue());
			       
			    }   
			        
           
			System.out.println("End"+compFailurData);
			
			
			
			
			
		};
		
		
	}
	
	
	private Map<Long,Integer> analyticResult()throws Exception{
		
		ComponentStatus componentStatus = new ComponentStatus();
		StringBuffer tokenStr = componentStatus.getToken();
		List<Component> compList = serviceImp.getCompStatus();
		
		Utility utility = new Utility();
		Data data = new Data();
		DataSet data_set = new DataSet();
		Constants constants = new Constants();
		List<String> cycles =null;
		Map<Long,Integer> failureCompData = new HashMap<Long,Integer>() ;
		ObjectMapper mapper = new ObjectMapper();
		
		Calendar cal = Calendar.getInstance(); 
		Date today = cal.getTime(); 
		Date mnfgDate =null;
		long dateSubtract =0;
		 long time=0;
		 String jsonInString =null;
		 AccessToken accessToken =null;
		 AnalyticReport analyticReport = null;
		 String resultSubString = null;
		 int failureProb = 0;
		 
		
		 
		for(Component comp : compList){
			
		
			String tempCycles=comp.getCycles();
			if(tempCycles.equals("null")){
				System.out.println("component id"+comp.getComponentID()+" cycles"+comp.getCycles());
				continue;
			}
			
			cycles= new ArrayList<String>();
			cal.setTime(comp.getMnfgDate());
			
			mnfgDate = cal.getTime(); 

	 
	        dateSubtract = today.getTime() - mnfgDate.getTime(); 
	        time = 1000 * 60 * 60 * 24 ; 
			long currentDtd = dateSubtract/time;
			constants.setCurrent_age(String.valueOf(currentDtd));
			constants.setFuture_age(String.valueOf(currentDtd + 200));
			constants.setReliable_method("reliable");
			
			
			cycles.add(comp.getCycles());
			data_set.setCycles(cycles);
			//data_set.setCycles(new ArrayList<String>().add(comp.getCycles()));
			data.setData_set(data_set);
			data.setConstants(constants);
			
			utility.setData(data);
			
			
			jsonInString = mapper.writeValueAsString(utility);
			
			accessToken = mapper.readValue(tokenStr.toString(), AccessToken.class);
			analyticReport =  mapper.readValue(componentStatus.sendDataWithToken(accessToken.getAccess_token(), jsonInString), AnalyticReport.class);
		
			resultSubString = analyticReport.getResult().substring(1, analyticReport.getResult().length()-1);
			failureProb = Integer.valueOf(resultSubString.substring(2,4));
			failureCompData.put(comp.getComponentID(), failureProb);
			
		}
		return failureCompData;
	}
}
