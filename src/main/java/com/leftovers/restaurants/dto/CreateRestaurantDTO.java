package com.leftovers.restaurants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;
import java.sql.Time;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRestaurantDTO {
    @NotNull
    @NotBlank(message = "Restaurant name is required")
    public String name;

    @NotNull
    @NotBlank(message = "Zipcode is required")
    @Pattern(regexp = "^\\d{5}(?:[-\\s]\\d{4})?$")
    public String zipcode;

    @NotNull
    @NotBlank(message = "City is required")
    public String city;

    @NotNull
    @NotBlank(message = "State is required")
    public String state;

    public String country;

    @NotNull
    @NotBlank(message = "Street Address is required")
    public String streetAddress;

    public String unitNumber;

    @NotNull
    @NotBlank(message = "Phone number is required")
    //@Pattern(regexp = "^\\d{10,15}$", message = "Phone number must be between 10 and 15 numbers")
    public String phoneNo;

    public String website;

    @NotNull(message = "Opening time is required")
    public Time openTime;

    @NotNull(message = "Closing time is required")
    public Time closeTime;
}
