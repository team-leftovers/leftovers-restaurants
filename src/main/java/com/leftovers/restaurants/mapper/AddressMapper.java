package com.leftovers.restaurants.mapper;

import com.leftovers.restaurants.dto.AddressDTO;
import com.leftovers.restaurants.dto.CreateRestaurantDTO;
import com.leftovers.restaurants.model.Address;

public class AddressMapper {
    public static Address toAddress(CreateRestaurantDTO dto) {
        return Address.builder()
                .zipcode(dto.zipcode)
                .city(dto.city)
                .state(dto.state)
                .country(dto.country)
                .streetAddress(dto.streetAddress)
                .unitNumber(dto.unitNumber)
                .build();
    }

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
