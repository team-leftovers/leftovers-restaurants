package com.leftovers.restaurants.controller;

import com.leftovers.restaurants.dto.UpdateFoodDto;
import com.leftovers.restaurants.dto.UpdateRestaurantDto;
import com.leftovers.restaurants.model.Food;
import com.leftovers.restaurants.model.Restaurant;
import com.leftovers.restaurants.service.FoodService;
import com.leftovers.restaurants.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/food")
@RequiredArgsConstructor
public class FoodController {
    private static final String MAPPING = "/food";
    private final FoodService service;


    @RequestMapping(path = "", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Food>> getAllFood() {
        log.info("GET Food");
        var food = service.getAllFood();
        if(food.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(food);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Food> getFoodById(@PathVariable Integer id) {
        log.info("GET Food " + id);
        return ResponseEntity.of(Optional.ofNullable(service.getFood(id)));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Food> updateFood(@PathVariable Integer id, UpdateFoodDto dto) {
        log.info("PUT Restaurant " + id);
        return ResponseEntity.of(Optional.ofNullable(service.updateFood(id, dto)));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteFood(@PathVariable Integer id) {
        log.info("DELETE Food " + id);
        service.deleteFood(id);
        return ResponseEntity.noContent().build();
    }
}
