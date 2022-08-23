package com.leftovers.restaurants.mapper;

import com.leftovers.restaurants.dto.*;
import com.leftovers.restaurants.model.Address;
import com.leftovers.restaurants.model.Restaurant;

import java.util.stream.Collectors;

public class RestaurantMapper {
    public static Restaurant toRestaurant(CreateRestaurantDTO dto) {
        return Restaurant.builder()
                .name(dto.name)
                .phoneNo(dto.phoneNo)
                .website(dto.website)
                .openTime(dto.openTime)
                .closeTime(dto.closeTime)
                .address(Address.builder()
                        .zipcode(dto.zipcode)
                        .city(dto.city)
                        .state(dto.state)
                        .country(dto.country)
                        .streetAddress(dto.streetAddress)
                        .unitNumber(dto.unitNumber)
                    .build())
            .build();
    }

    public static Restaurant toRestaurant(UpdateRestaurantDTO dto) {
        return Restaurant.builder()
                .id(dto.id)
                .name(dto.name)
                .phoneNo(dto.phoneNo)
                .website(dto.website)
                .openTime(dto.openTime)
                .closeTime(dto.closeTime)
                .addressId(dto.addressId)
                .address(Address.builder()
                        .id(dto.addressId)
                        .zipcode(dto.zipcode)
                        .city(dto.city)
                        .state(dto.state)
                        .country(dto.country)
                        .streetAddress(dto.streetAddress)
                        .unitNumber(dto.unitNumber)
                        .latitude(dto.latitude)
                        .longitude(dto.longitude)
                    .build())
            .build();
    }

    public static ShortRestaurantDTO toShortDTO(Restaurant restaurant) {
        return ShortRestaurantDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .openTime(restaurant.getOpenTime())
                .closeTime(restaurant.getCloseTime())
                .rating(restaurant.getRating())
            .build();
    }

    public static FullRestaurantDTO toFullDTO(Restaurant restaurant) {
        return FullRestaurantDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .phoneNo(restaurant.getPhoneNo())
                .website(restaurant.getWebsite())
                .openTime(restaurant.getOpenTime())
                .closeTime(restaurant.getCloseTime())
                .rating(restaurant.getRating())
                .ratingCount(restaurant.getRatingCount())
                .address(AddressMapper.toDTO(restaurant.getAddress()))
                .food(restaurant.getMenuItems()
                        .stream()
                        .map(item -> FoodMapper.toShortDTO(item))
                        .collect(Collectors.toList()))
            .build();
    }
}
