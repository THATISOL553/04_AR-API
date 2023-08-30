package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.his.binding.CitizenApp;
import com.his.service.ArService;

@RestController
public class ArRestController {

	@Autowired
	private ArService arService;
	
	@PostMapping("/app")
	public ResponseEntity<String> createCitizenApp(@RequestBody CitizenApp app){
		Integer appId = arService.createApplication(app);
		if(appId > 0) {
			return new ResponseEntity<> ("App Created with App Id:  " + appId, HttpStatus.OK);
			
		}else {
			
			return new ResponseEntity<> ("Invalid SSN", HttpStatus.BAD_REQUEST);
		}
	}
	
	
}

/*
Application Registration (AR) module contains Citizen Onboarding functionality & it will verify 
citizenship of the citizen. If the citizen belongs to NJ state then application get cretaed, and it 
should return application Id as response and onboarding will be success,Otherwise onboarding will be failed.

Note: If citizen belongs to NJ state, then only citizen can apply for the plan otherwise we should return "Invalid Response"
and can't apply for the paln.
*/