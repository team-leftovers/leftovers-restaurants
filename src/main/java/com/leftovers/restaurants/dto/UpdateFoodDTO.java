package com.leftovers.restaurants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateFoodDTO {
    @NotNull(message = "Food id is required")
    public Integer id;

    @NotNull
    @NotBlank(message = "Name is required")
    public String name;

    public String description;

    @NotNull(message = "Price is required")
    public BigDecimal price;

    @NotNull(message = "Restaurant id is required")
    public Integer restaurantId;
}