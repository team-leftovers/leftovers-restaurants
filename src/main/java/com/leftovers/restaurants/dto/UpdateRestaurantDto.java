package com.leftovers.restaurants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRestaurantDto {
    public String name;

    @Pattern(regexp = "^\\d{5}(?:[-\\s]\\d{4})?$")
    public String zipcode;

    public String state;

    public String country;

    public String streetAddress;

    public String houseNumber;

    public String unitNumber;

    @Pattern(regexp = "^\\d{10,15}$", message = "Phone number must be between 10 and 15 numbers")
    public String phoneNo;

    public String website;

    public Time openTime;

    public Time closeTime;
}
