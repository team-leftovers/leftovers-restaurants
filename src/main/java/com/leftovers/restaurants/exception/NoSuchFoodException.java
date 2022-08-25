package com.leftovers.restaurants.exception;

import java.util.NoSuchElementException;
import java.util.Optional;

public class NoSuchFoodException extends NoSuchElementException {
    private final Integer id;

    public NoSuchFoodException(Integer id) {
        super("No food found with id=" + id);
        this.id = id;
    }

    public Optional<Integer> getFoodId() {
        return Optional.ofNullable(id);
    }
}
