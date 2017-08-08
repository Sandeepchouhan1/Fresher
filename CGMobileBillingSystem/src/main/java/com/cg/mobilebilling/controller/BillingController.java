package com.cg.mobilebilling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.services.BillingServices;


@RestController
public class BillingController {
	
	@Autowired
	private BillingServices services;
	
	@RequestMapping(value="/acceptCustomerDetail",method=RequestMethod.POST,consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String>acceptProductDetail(@ModelAttribute Customer customer) throws BillingServicesDownException{
		services.acceptCustomerDetails(customer);
		return new ResponseEntity<>("Customer details succesfully added",HttpStatus.OK);
		
	}

}
