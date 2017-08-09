package com.cg.billing.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.billing.customresponse.CustomResponse;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;


public class BillingsystemExceptionAspect {

	@ControllerAdvice(basePackages={"com.cg.onlineshop.controllers"})
	public class ProductCatlogServicesExceptionAspect {

		@ExceptionHandler(CustomerDetailsNotFoundException.class)
		public ResponseEntity<CustomResponse> handelProductDetailsNotFoundException(Exception e){
			CustomResponse response = new CustomResponse(HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
			System.out.println("Sop Statement");
			return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);			
		}
	
}}
