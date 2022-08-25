package com.leftovers.restaurants.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.sql.Time;

@Builder
public class ShortRestaurantDTO {
    public Integer id;
    public String name;
    public Time openTime;
    public Time closeTime;
    public BigDecimal rating;
}