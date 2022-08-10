package com.leftovers.restaurants.controller;

import com.leftovers.restaurants.dao.FoodDao;
import com.leftovers.restaurants.model.Food;
import com.leftovers.restaurants.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/food")
public class FoodController {

    @Autowired
    FoodService foodService;

    @GetMapping(path = "")
    public List<Food> getAll() {
        return foodService.getAll();
    }
    @RequestMapping(path = "/addFood" , method = RequestMethod.POST)
    public String addFood(@RequestBody Food food) {
        System.out.println(food.getName());
        if (foodService.addFood(food))
        {
            return "Item Added Succesfully";
        }
        return "An Error Occured";

    }

    @RequestMapping(path = "/by-id/{id}" , method = RequestMethod.GET)
    public Optional<Food> getFoodById(@PathVariable Integer id) {
        return foodService.getFoodById(id);
    }

    @RequestMapping(path = "/{name}" , method = RequestMethod.GET)
    public List<Food> getFoodByName(@PathVariable String name) {
        return foodService.getFoodByName(name);
    }

    @PutMapping(path = "/update-name/{id}")
    public String updateFoodName(@PathVariable Integer id , @RequestParam String name ) {
        if (foodService.updateFoodName(id , name)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }
    @PutMapping(path = "/update-price/{id}")
    public String updateFoodPricce(@PathVariable Integer id , @RequestParam float price ) {
        if (foodService.updateFoodPrice(id , price)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }

    @PutMapping(path = "/update-description/{id}")
    public String updateFoodDescription(@PathVariable Integer id , @RequestParam String description ) {
        if (foodService.updateFoodDescription(id , description)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }

    @PutMapping(path = "/update-restaurant/{id}")
    public String updateFoodRestaurant(@PathVariable Integer id , @RequestParam Integer restaurantId ) {
        if (foodService.updateFoodRestaurant(id , restaurantId)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }

    @DeleteMapping(path = "/delete")
    public String deleteByid(@RequestParam Integer id) {
        return foodService.deleteById(id);
    }
}
