package com.leftovers.restaurants.service;

import com.leftovers.restaurants.dto.CreateFoodDto;
import com.leftovers.restaurants.dto.UpdateFoodDto;
import com.leftovers.restaurants.exception.NoSuchFoodException;
import com.leftovers.restaurants.exception.NoSuchRestaurantException;
import com.leftovers.restaurants.model.Food;
import com.leftovers.restaurants.repository.FoodRepository;
import com.leftovers.restaurants.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepo;
    private final RestaurantRepository restRepo;

    @Transactional
    @Override
    public Food createNewFood(CreateFoodDto dto) {
        notNull(dto);
//        var restaurant = restRepo.findRestaurantById(dto.restaurantId);
//        if(restaurant.isEmpty())
//            throw new NoSuchRestaurantException(dto.restaurantId);

        //try catch foreign key exception
        Food food = Food.builder()
                .name(dto.name)
                .description(dto.description)
                .price(dto.price)
//                .restaurant(dto.restaurantId)
                .restaurantId(dto.restaurantId)
            .build();

        return foodRepo.save(food);
    }

    @Override
    public List<Food> getAllFood() {
        return foodRepo.findAll();
    }

    @Override
    public Food getFood(Integer id) {
        notNull(id);
        return foodRepo.findFoodById(id)
                .orElseThrow(() -> new NoSuchFoodException(id));
    }

    @Transactional
    @Override
    public Food updateFood(Integer id, UpdateFoodDto dto) {
        notNull(id, dto);
        var result = foodRepo.findFoodById(id);
        if(result.isEmpty())
            throw new NoSuchFoodException(id);

        Food food = result.get();
        food.setName(dto.name);
        food.setPrice(dto.price);
        food.setDescription(dto.description);

        return foodRepo.save(food);
    }

    @Transactional
    @Override
    public void deleteFood(Integer id) {
        notNull(id);
        if(foodRepo.deleteFoodById(id) == 0)
            throw new NoSuchFoodException(id);
    }


    // Utility function to determine if input was incorrectly null
    private void notNull(Object... ids) {
        for(var id: ids) {
            if(id == null)
                throw new IllegalArgumentException("Expected value but received null.");
        }
    }
}
