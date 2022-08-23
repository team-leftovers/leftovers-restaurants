package com.leftovers.restaurants.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetRestaurantDto {
    public Integer id;
    public String name;
    public Time openTime;
    public Time closeTime;
}
