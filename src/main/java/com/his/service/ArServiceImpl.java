package com.his.service;

import java.lang.annotation.Annotation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.his.binding.CitizenApp;
import com.his.entity.CitizenApiEntity;
import com.his.repository.CitizenAppRepository;

@Service
public class ArServiceImpl implements ArService{

	@Autowired
	private CitizenAppRepository appRepo;
	
	@Override
	public Integer createApplication(CitizenApp app) {
	
	//Make rest call to SSA-WEB api with SSN as input
		String endpointUrl = "http://localhost:8085/state/{ssn}";
		
		/*
		 * RestTemplate rt = new RestTemplate(); ResponseEntity<String> responseEntity =
		 * rt.getForEntity(endpointUrl, String.class, app.getSsn()); String stateName =
		 * responseEntity.getBody();
		 */
		
		String stateName = WebClient
								.create()  // Creates webclient instance
								.get()     // Represents Get request
								.uri(endpointUrl, app.getSsn())  //Represents url to send request
								.retrieve()        //To retrieve response
								.bodyToMono(String.class) //To mention the response type
								.block();  //To make synchronous call
		
		if("New Jersey".equals(stateName)) {
			//Create application;
			CitizenApiEntity entity = new CitizenApiEntity();
			BeanUtils.copyProperties(app, entity);
			
			entity.setStateName(stateName);
			CitizenApiEntity citizenApiEntity = appRepo.save(entity);
			
			return citizenApiEntity.getAppId();
		}
		return 0;
	}


}
