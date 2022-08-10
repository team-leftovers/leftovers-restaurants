package com.leftovers.restaurants.service;

import com.leftovers.restaurants.dao.FoodDao;
import com.leftovers.restaurants.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Component
public class FoodService {

    @Autowired
    FoodDao foodDao;
    public boolean addFood(Food food) {
        try {
            foodDao.addFood(food);
        }
        catch (Exception e)
        {
            throw e;
        }
        return true;
    }

    public Optional<Food> getFoodById(Integer id) {
        try {
            return foodDao.getFoodById(id);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public List<Food> getFoodByName(String name) {
        try {
            return foodDao.getFoodByName(name);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public List<Food> getAll() {
        return foodDao.getAll();
    }

    public boolean updateFoodName(Integer id, String name) {
        Optional<Food> foodFromDb = foodDao.getFoodById(id);
        Food foodObject = foodFromDb.get();
        foodObject.setName(name);
        if (foodDao.UpdateFood(foodObject))
        {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean updateFoodPrice(Integer id, float price) {
        Optional<Food> foodFromDb = foodDao.getFoodById(id);
        Food foodObject = foodFromDb.get();
        foodObject.setPrice(price);
        if (foodDao.UpdateFood(foodObject))
        {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean updateFoodDescription(Integer id, String description) {
        Optional<Food> foodFromDb = foodDao.getFoodById(id);
        Food foodObject = foodFromDb.get();
        foodObject.setDescription(description);
        if (foodDao.UpdateFood(foodObject))
        {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean updateFoodRestaurant(Integer id, Integer restaurantId) {
        Optional<Food> foodFromDb = foodDao.getFoodById(id);
        Food foodObject = foodFromDb.get();
        foodObject.setRestaurantId(restaurantId);
        if (foodDao.UpdateFood(foodObject))
        {
            return true;
        }
        else {
            return false;
        }
    }

    public String deleteById(Integer id) {
        Optional<Food> foodFromDb = foodDao.getFoodById(id);
        Food food = foodFromDb.get();
        if (foodFromDb.equals(null)){
            return "Nothing Present at Db by this ID";
        }

        else if (foodDao.deleteEntry(food)) {
            return "Entry delted Successfully";
        }
        else {
            return "Something Went Wrong check logs";
        }
    }
}
