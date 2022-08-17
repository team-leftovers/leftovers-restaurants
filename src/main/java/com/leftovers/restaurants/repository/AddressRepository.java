package com.leftovers.restaurants.repository;

import com.leftovers.restaurants.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    Address findAddressById(Integer id);
}
