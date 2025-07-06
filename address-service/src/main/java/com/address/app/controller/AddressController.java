package com.address.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.address.app.request.CreateAddressRequest;
import com.address.app.response.AddressResponse;
import com.address.app.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	Logger logger = LoggerFactory.getLogger(AddressController.class);

	@Autowired
	private AddressService addressService;

	@PostMapping("/create")
	public ResponseEntity<AddressResponse> createAddress(@RequestBody CreateAddressRequest request) {
		try {
			AddressResponse addressResponse = addressService.createAddress(request);
			return ResponseEntity.status(HttpStatus.CREATED).body(addressResponse);
		} catch (Exception e) {
			logger.error("Unable to create Address" + e.getMessage());
		}
		return null;

	}

	@GetMapping("getById/{id}")
	public ResponseEntity<AddressResponse> getAddressById(@PathVariable long id) {
		try {
			AddressResponse addressResponse = addressService.getAddressById(id);
			return ResponseEntity.ok(addressResponse);
		} catch (Exception e) {
			logger.error("Unable to get Address for ID " + id);
		}
		return null;
	}

}
