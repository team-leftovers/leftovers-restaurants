package com.leftovers.restaurants.controller;

import com.leftovers.restaurants.dao.AddressDao;
import com.leftovers.restaurants.dao.RestaurantDao;
import com.leftovers.restaurants.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;

@RestController
@RequestMapping(path = "/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantDao restaurants;

    @Autowired
    AddressDao addresses;


    // Restaurant Generation
    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Restaurant> postRestaurant(@RequestBody Restaurant r) {
        System.out.println("========\n" + r);
        if(!restaurantInputValidation(r))
            return new ResponseEntity<Restaurant>(HttpStatus.NOT_ACCEPTABLE);

        if(!restaurants.addRestaurant(r))
            return new ResponseEntity<Restaurant>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<Restaurant>(r,HttpStatus.CREATED);
    }

    boolean restaurantInputValidation(Restaurant r) {
        // Check if id exists
        if(r.getId() != null) {
            var result = restaurants.getRestaurant(r.getId());
            if(result != null) return false;
        }

        return r.getName() != null && r.getAddressId() != null && r.getPhoneNo() != null
                && r.getCloseTime() != null && r.getOpenTime() != null;
    }
}
