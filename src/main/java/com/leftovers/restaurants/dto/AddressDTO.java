package com.leftovers.restaurants.dto;

import lombok.Builder;

@Builder
public class AddressDTO {
    public Integer id;
    public Double latitude;
    public Double longitude;
    public String zipcode;
    public String city;
    public String state;
    public String country;
    public String streetAddress;
    public String unitNumber;
}
