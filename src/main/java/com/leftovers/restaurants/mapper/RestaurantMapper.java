package com.leftovers.restaurants.mapper;

import com.leftovers.restaurants.dto.*;
import com.leftovers.restaurants.model.Food;
import com.leftovers.restaurants.model.Restaurant;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class RestaurantMapper {
    public static Restaurant toRestaurant(CreateRestaurantDTO dto) {
        return Restaurant.builder()
                .name(dto.name)
                .phoneNo(dto.phoneNo)
                .website(dto.website)
                .openTime(dto.openTime)
                .closeTime(dto.closeTime)
                .menuItems(new ArrayList<Food>())
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
