package com.leftovers.restaurants.mapper;

import com.leftovers.restaurants.dto.AddressDTO;
import com.leftovers.restaurants.model.Address;

public class AddressMapper {
    public static AddressDTO toDTO(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .zipcode(address.getZipcode())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .streetAddress(address.getStreetAddress())
                .unitNumber(address.getUnitNumber())
            .build();
    }
}
