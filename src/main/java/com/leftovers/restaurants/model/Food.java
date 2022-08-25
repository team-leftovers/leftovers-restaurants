package com.leftovers.restaurants.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "restaurant_id")
    private int restaurantId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="restaurant_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Restaurant restaurant;
}