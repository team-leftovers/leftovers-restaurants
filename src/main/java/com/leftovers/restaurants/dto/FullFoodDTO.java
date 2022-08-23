package com.leftovers.restaurants.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class FullFoodDTO {
    public Integer id;
    public String name;
    public String description;
    public BigDecimal price;
    public Integer restaurantId;
}