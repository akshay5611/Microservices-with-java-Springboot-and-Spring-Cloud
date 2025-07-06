package com.student.app.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.student.app.response.AddressResponse;

@FeignClient(value = "api-gateways")
public interface AddressFeignClient {

	@GetMapping("/address-service/api/address/getById/{id}")
	public AddressResponse getAddressById(@PathVariable long id) ;
		
	
}
