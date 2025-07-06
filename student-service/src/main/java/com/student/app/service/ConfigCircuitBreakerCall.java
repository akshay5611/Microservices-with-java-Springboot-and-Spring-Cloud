package com.student.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.app.feignclient.AddressFeignClient;
import com.student.app.response.AddressResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ConfigCircuitBreakerCall {
	
	Logger logger = LoggerFactory.getLogger(ConfigCircuitBreakerCall.class);
	
	@Autowired
	AddressFeignClient addressFeignClient;
	
	long count = 1;
	
	@CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
	public AddressResponse getAddressById(long addressId) {
		logger.info("count is "+ count);
		count++;
		return addressFeignClient.getAddressById(addressId);
	}
	
	public AddressResponse fallbackGetAddressById(long addressId , Throwable th) {
		logger.error("fallback methid is calling "+ th.getMessage());
		return new AddressResponse();
	}

}
