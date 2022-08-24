package com.leftovers.restaurants.repository;

import com.leftovers.restaurants.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    Optional<Address> findAddressById(Integer id);
}
