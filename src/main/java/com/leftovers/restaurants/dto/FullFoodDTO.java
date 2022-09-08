package com.leftovers.restaurants.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Set;

@Builder
public class FullFoodDTO {
    public Integer id;
    public String name;
    public String description;
    public BigDecimal price;
    public Integer restaurantId;
    public Set<TagDTO> tags;
}