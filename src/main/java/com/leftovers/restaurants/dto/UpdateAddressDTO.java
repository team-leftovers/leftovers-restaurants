package com.leftovers.restaurants.dto;

import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
public class UpdateAddressDTO {
    @NotNull(message = "Address id is required")
    public Integer id;
    @Pattern(regexp = "^\\d{5}(?:[-\\s]\\d{4})?$")
    public String zipcode;
    public String city;
    public String state;
    public String country;
    public String streetAddress;
    public String unitNumber;
    public Double latitude;
    public Double longitude;
}
