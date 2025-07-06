package com.address.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.address.app.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
