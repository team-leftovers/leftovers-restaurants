package com.leftovers.restaurants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRestaurantDTO {
    public String name;
    public String phoneNo;
    public String website;
    public Time openTime;
    public Time closeTime;
}
