package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	List<Address> findAllByCity(String city);

	List<Address> findAllByCountry(String country);

	List<Address> findAllByStreet(String street);

	List<Address> findAllByPostalCode(String postalCode);
}
