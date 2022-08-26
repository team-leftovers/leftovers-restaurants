package com.leftovers.restaurants.repository;

import com.leftovers.restaurants.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findAddressById(Integer id);
}
