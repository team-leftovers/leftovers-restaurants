package com.leftovers.restaurants.mapper;

import com.leftovers.restaurants.dto.CreateFoodDTO;
import com.leftovers.restaurants.dto.FullFoodDTO;
import com.leftovers.restaurants.dto.ShortFoodDTO;
import com.leftovers.restaurants.model.Food;
import com.leftovers.restaurants.model.Tag;

import java.util.HashSet;
import java.util.stream.Collectors;

public class FoodMapper {
    public static Food toFood(CreateFoodDTO dto) {
        return Food.builder()
                .name(dto.name)
                .description(dto.description)
                .price(dto.price)
                .restaurantId(dto.restaurantId)
                .foodTags(new HashSet<Tag>())
            .build();
    }

    public static ShortFoodDTO toShortDTO(Food food) {
        return ShortFoodDTO.builder()
                .id(food.getId())
                .name(food.getName())
                .price(food.getPrice())
            .build();
    }

    public static FullFoodDTO toFullDTO(Food food) {
        return FullFoodDTO.builder()
                .id(food.getId())
                .name(food.getName())
                .description(food.getDescription())
                .price(food.getPrice())
                .restaurantId(food.getRestaurantId())
                .tags(food.getFoodTags()
                        .stream()
                        .map(item -> TagMapper.toDto(item))
                        .collect(Collectors.toSet()))
            .build();
    }
}
