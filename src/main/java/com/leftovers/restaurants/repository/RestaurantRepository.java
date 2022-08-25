package com.leftovers.restaurants.repository;

import com.leftovers.restaurants.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Optional<Restaurant> findRestaurantById(Integer id);
    Optional<Restaurant> findTopByOrderByIdDesc();
    List<Restaurant> findRestaurantByNameContainingIgnoreCase(String str);
    List<Restaurant> findAll();
    int deleteRestaurantById(Integer id);
}