package com.leftovers.restaurants.dao;

import com.leftovers.restaurants.model.Food;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends CrudRepository<Food , Integer> {
    List<Food> findByName(String name);
    Food findById(long id);
}
