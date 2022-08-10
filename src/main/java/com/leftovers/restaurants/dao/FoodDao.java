package com.leftovers.restaurants.dao;

import com.leftovers.restaurants.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class FoodDao {

    @Autowired
    FoodRepository fdr;

    public boolean addFood(Food food) {

        try {
            fdr.save(food);
        }
        catch (Exception e) {
            throw e;
        }
        return true;
    }

    public Optional<Food> getFoodById(Integer id) {
        var result = fdr.findById(id);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }

    public List<Food> getFoodByName(String name) {
        var result = fdr.findByName(name);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }

    public List<Food> getAll() {
        var result = fdr.findAll();
        if (result != null) {
            return (List<Food>) result;
        }
        else {
            return null;
        }
    }

    public boolean UpdateFood(Food food) {
        try {
            fdr.save(food);
        }
        catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
        return true;
    }

    public boolean deleteEntry(Food food) {
        try {
            fdr.delete(food);
            return true;
        }
        catch (NoSuchElementException e) {
            throw e;
        }
        catch (Exception e) {
            throw e;
        }
    }
}
