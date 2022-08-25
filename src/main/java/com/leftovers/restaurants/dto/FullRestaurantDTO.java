package com.leftovers.restaurants.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;

@Builder
public class FullRestaurantDTO {
    public Integer id;
    public String name;
    public String phoneNo;
    public String website;
    public Time openTime;
    public Time closeTime;
    public BigDecimal rating;
    public Integer ratingCount;
    public AddressDTO address;
    public List<ShortFoodDTO> food;
}
