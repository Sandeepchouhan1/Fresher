package com.cg.billing.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.exceptions.BillingServicesDownException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.services.IBillingServices;



@RestController
public class BillingController {
	
	@Autowired
	private IBillingServices services;
	
	@RequestMapping(value="/acceptCustomerDetail",method=RequestMethod.POST,consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String>acceptcustomerDetail(@ModelAttribute Customer customer) throws BillingServicesDownException{
		services.acceptCustomerDetails(customer);
		return new ResponseEntity<>("Customer details succesfully added",HttpStatus.OK);
		
	}

	@RequestMapping(value="/deleteCustomer/{customerID}",method=RequestMethod.DELETE)
	public ResponseEntity<String>deletecustomerDetail(@PathVariable("customerID") int customerID) throws BillingServicesDownException, CustomerDetailsNotFoundException{
		boolean check=services.deleteCustomer(customerID);
		if(check==true)
		return new ResponseEntity<>("Customer details succesfully deleted",HttpStatus.OK);
		else
			throw new CustomerDetailsNotFoundException("Customer details with Customer ID  \" + customerID + \" not Found");
	}
	
	@RequestMapping(value={"/CustomerDetails/{customerID}"},produces=MediaType.APPLICATION_JSON_VALUE,headers ="Accept=application/json")
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable("customerID") int customerID) throws CustomerDetailsNotFoundException, BillingServicesDownException{
		Customer customer = services.getCustomerDetails(customerID);
		if(customer==null)throw new CustomerDetailsNotFoundException("Product details with productCode "+customerID+" not found");
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/allCustomerDetailsJSON"},produces=MediaType.APPLICATION_JSON_VALUE,
			headers ="Accept=application/json")
	public ResponseEntity<ArrayList<Customer>> getAllCustomerDetailsJSON() throws BillingServicesDownException{
		
		ArrayList<Customer> customerList = (ArrayList<Customer>) services.getAllCustomerDetails();
		return new ResponseEntity<>(customerList,HttpStatus.OK);
	}
	
	@RequestMapping(value="/openPostPaidAccount/{}",method=RequestMethod.DELETE)
	public ResponseEntity<String>openPostPaidAccount(@PathVariable("customerID") int customerID,@PathVariable("planID") int planID) throws BillingServicesDownException, CustomerDetailsNotFoundException, PlanDetailsNotFoundException{
		services.openPostpaidMobileAccount(customerID,planID);
		return new ResponseEntity<>("Postpaid Account open Successfully",HttpStatus.OK);
	}
	
	@RequestMapping(value="/acceptPlanDetail",method=RequestMethod.POST,consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String>acceptPlanDetail(@ModelAttribute Plan plan) throws BillingServicesDownException, PlanDetailsNotFoundException{
		Plan plan1=services.acceptPlanDetail(plan);
		return new ResponseEntity<>("Customer details succesfully added",HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
