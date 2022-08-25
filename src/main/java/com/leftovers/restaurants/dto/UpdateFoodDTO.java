package com.leftovers.restaurants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateFoodDTO {
    public String name;
    public String description;
    public BigDecimal price;
}