package com.leftovers.restaurants.exception;

import java.util.NoSuchElementException;
import java.util.Optional;

public class NoSuchAddressException extends NoSuchElementException {
    private final Integer id;

    public NoSuchAddressException(Integer id) {
        super("No food found with id=" + id);
        this.id = id;
    }

    public Optional<Integer> getAddressId() {
        return Optional.ofNullable(id);
    }
}