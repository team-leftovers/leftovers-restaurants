package com.leftovers.restaurants.controller;

import com.leftovers.restaurants.dto.CreateRestaurantDTO;
import com.leftovers.restaurants.dto.UpdateRestaurantDTO;
import com.leftovers.restaurants.model.Restaurant;
import com.leftovers.restaurants.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private static final String MAPPING = "/restaurants";
    private final RestaurantService service;


    @RequestMapping(path = "", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Restaurant> postRestaurant(@Valid @RequestBody CreateRestaurantDTO dto) {
        log.info("POST Restaurant");
        var restaurant = service.createNewRestaurant(dto);
        var uri = URI.create(MAPPING + "/" + restaurant.getId());
        return ResponseEntity.created(uri).body(restaurant);
    }

    @RequestMapping(path = "", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        log.info("GET Restaurants");
        var restaurants = service.getAllRestaurants();
        if(restaurants.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(restaurants);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Integer id) {
        log.info("GET Restaurant " + id);
        return ResponseEntity.of(Optional.ofNullable(service.getRestaurant(id)));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Integer id,
                                                       @Valid @RequestBody UpdateRestaurantDTO dto) {
        log.info("PUT Restaurant " + id);
        return ResponseEntity.of(Optional.ofNullable(service.updateRestaurant(id, dto)));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteRestaurant(@PathVariable Integer id) {
        log.info("DELETE Restaurant " + id);
        service.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}
