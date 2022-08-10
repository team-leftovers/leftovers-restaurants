package com.leftovers.restaurants.model;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Food {

    @Id
    @Column(name = "id" , nullable = false)
    private int id;
    @Column(name = "name")
    private String name;

    @Column(name = "restaurant_id")
    private Integer restaurantId;
    @Column(name = "price")
    private float price;
    @Column(name = "description")
    private String description;

    public Food() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
