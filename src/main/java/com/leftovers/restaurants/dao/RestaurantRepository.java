package com.leftovers.restaurants.dao;

import com.leftovers.restaurants.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
    Restaurant findRestaurantById(Integer id);
    Restaurant findRestaurantByName(String name);
    Restaurant findTopByOrderByIdDesc();
}