package com.leftovers.restaurants.exception;

import java.util.NoSuchElementException;

public class NoSuchRestaurantTagException extends NoSuchElementException {
    private final Integer restaurantID;
    private final Integer tagId;

    public NoSuchRestaurantTagException(Integer restaurantID, Integer tagId) {
        super("No tag with id=" + tagId + " in restaurant with id=" + restaurantID);
        this.restaurantID = restaurantID;
        this.tagId = tagId;
    }
}
