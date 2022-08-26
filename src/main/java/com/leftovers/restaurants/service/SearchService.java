package com.leftovers.restaurants.service;

import com.leftovers.restaurants.dto.GetRestaurantDto;
import com.leftovers.restaurants.exception.NoSuchFoodException;
import com.leftovers.restaurants.model.Food;
import com.leftovers.restaurants.model.Restaurant;
import com.leftovers.restaurants.repository.FoodRepository;
import com.leftovers.restaurants.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ModelMapper modelMapper;
    private final FoodRepository foodRepo;
    private final RestaurantRepository restRepo;

//    private final RestaurantService restaurantService;

    public Set<GetRestaurantDto> getRestaurantsByFood(String searchTerm , List<String> tags)
    {
        List<Food> foods = foodRepo.findFoodBySearchTermWithTags(searchTerm , tags); //searching foods
        List<Restaurant> restaurants = restRepo.findRestaurantBySearchTermWithTags(searchTerm , tags);   //searching restaurants

//        if(foods.isEmpty() && restaurants.isEmpty()) //check if both are empty
//            throw new NoSuchFoodException(1); // cause i am not searching for ID i am searching for a name that why send 1 to default the case

        List<Restaurant> rests = foods.stream().map(food -> {
            return food.getRestaurant();}).collect(Collectors.toList()); //get restaurants object from foods

        Set<Restaurant> result = new HashSet<>();
        result.addAll(restaurants);
        result.addAll(rests);
        return result.stream().map(res -> {return convertEntityToDto(res);}).collect(Collectors.toSet());
    }

    public GetRestaurantDto convertEntityToDto(Restaurant restaurant) {
        GetRestaurantDto getRestaurantDto = modelMapper.map(restaurant , GetRestaurantDto.class);
        return getRestaurantDto;
    }
}
