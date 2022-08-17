package com.leftovers.restaurants.repository;

import com.leftovers.restaurants.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
    Restaurant findRestaurantById(Integer id);
    Restaurant findTopByOrderByIdDesc();
    List<Restaurant> findRestaurantByNameContainingIgnoreCase(String str);
}