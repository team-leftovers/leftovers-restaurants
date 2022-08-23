package com.leftovers.restaurants.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
public class CreateFoodDTO {
    @NotNull
    @NotBlank(message = "Name is required")
    public String name;

    public String description;

    @NotNull(message = "Price is required")
    public BigDecimal price;

    @NotNull(message = "Associated restaurant id is required")
    public Integer restaurantId;
}