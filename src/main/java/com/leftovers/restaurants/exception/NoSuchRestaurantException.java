package com.leftovers.restaurants.exception;

import java.util.NoSuchElementException;
import java.util.Optional;

public class NoSuchRestaurantException extends NoSuchElementException {
    private final Integer id;

    public NoSuchRestaurantException(Integer id) {
        super("No restaurant found with id=" + id);
        this.id = id;
    }

    public Optional<Integer> getFoodId() {
        return Optional.ofNullable(id);
    }
}
