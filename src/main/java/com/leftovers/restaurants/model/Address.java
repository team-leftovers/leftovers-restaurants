package com.leftovers.restaurants.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "zip_code", nullable = false)
    private Integer zipCode;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @Column(name = "house_number", length = 5)
    private String houseNumber;

    @Column(name = "unit_number", length = 5)
    private String unitNumber;
}