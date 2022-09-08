package com.leftovers.restaurants.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;
import java.util.Set;

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
    public Set<TagDTO> tags;
}
