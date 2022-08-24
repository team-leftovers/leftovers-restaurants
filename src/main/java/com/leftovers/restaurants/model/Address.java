package com.leftovers.restaurants.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "latitude", nullable = false)
    @Builder.Default
    private Double latitude = 0.0;

    @Column(name = "longitude", nullable = false)
    @Builder.Default
    private Double longitude = 0.0;

    @Column(name = "zip_code", nullable = false)
    private String zipcode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    @Builder.Default
    private String country = "US";

    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @Column(name = "unit_number", length = 5)
    private String unitNumber;
}