package com.leftovers.restaurants.service;

import com.leftovers.restaurants.dto.*;

import java.util.List;

public interface RestaurantService {
    FullRestaurantDTO createNewRestaurant(CreateRestaurantDTO dto);
    List<ShortRestaurantDTO> getAllRestaurants();
    FullRestaurantDTO getRestaurant(Integer id);
    FullRestaurantDTO updateRestaurant(Integer id, UpdateRestaurantDTO dto);
    void deleteRestaurant(Integer id);
    AddressDTO updateRestaurantAddress(UpdateAddressDTO dto);
}
