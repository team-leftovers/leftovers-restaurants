package com.leftovers.restaurants.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class ShortFoodDTO {
    public Integer id;
    public String name;
    public BigDecimal price;
}