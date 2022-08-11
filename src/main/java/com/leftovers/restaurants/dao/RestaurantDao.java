package com.leftovers.restaurants.dao;

import com.leftovers.restaurants.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantDao {
    @Autowired
    RestaurantRepository repo;

    public boolean addRestaurant(Restaurant r) {
        try {
            repo.save(r);
        }
        catch(Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

     public Restaurant getRestaurant(Integer id) {
        return repo.findRestaurantById(id);
     }
}
