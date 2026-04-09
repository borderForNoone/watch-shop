package com.watch.shop.validation;

import com.watch.shop.model.enums.Colour;

import java.time.LocalDate;

public class WatchValidator {
    public static void validate(double price, Colour colour, LocalDate date) {
        if (price <= 0) throw new IllegalArgumentException("Price must be positive");
        if (colour == null) throw new IllegalArgumentException("Colour can't be null");
        if (date == null) throw new IllegalArgumentException("Date can't be null");
    }
}
