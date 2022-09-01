package com.leftovers.restaurants.exception;

import java.util.NoSuchElementException;
import java.util.Optional;

public class NoSuchTagException extends NoSuchElementException {
    private final Integer id;

    public NoSuchTagException(Integer id) {
        super("No tag found with id=" + id);
        this.id = id;
    }

    public Optional<Integer> getTagId() {
        return Optional.ofNullable(id);
    }
}
