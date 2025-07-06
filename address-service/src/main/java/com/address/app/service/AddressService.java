package com.address.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.address.app.entity.Address;
import com.address.app.repository.AddressRepository;
import com.address.app.request.CreateAddressRequest;
import com.address.app.response.AddressResponse;

@Service
public class AddressService {
	
	Logger logger = LoggerFactory.getLogger(AddressService.class);
	
	@Autowired
	private AddressRepository addressRepository;
	
	public AddressResponse createAddress(CreateAddressRequest request) {
		Address address = new Address();
		address.setCity(request.getCity());
		address.setStreet(request.getStreet());
		addressRepository.save(address);
		return new AddressResponse(address);
	}
	
	public AddressResponse getAddressById(long id) {
		logger.info("Inside getAddressById " + id);

		Address address = addressRepository.findById(id).get();

		return new AddressResponse(address);

	}

}
