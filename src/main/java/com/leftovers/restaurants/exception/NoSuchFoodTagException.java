package com.leftovers.restaurants.exception;

import java.util.NoSuchElementException;

public class NoSuchFoodTagException extends NoSuchElementException {
    private final Integer foodId;
    private final Integer tagId;

    public NoSuchFoodTagException(Integer foodId, Integer tagId) {
        super("No tag with id=" + tagId + " in restaurant with id=" + foodId);
        this.foodId = foodId;
        this.tagId = tagId;
    }
}