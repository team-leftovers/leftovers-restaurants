package com.leftovers.restaurants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateFoodDto {
    @NotNull
    @NotBlank(message = "Name is required")
    public String name;

    public String description;

    @NotNull(message = "Price is required")
    public BigDecimal price;
}