package com.watch.shop.validation;

import com.watch.shop.model.enums.Colour;
import com.watch.shop.model.enums.Manufacturer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WatchValidator {
    public static void validate(BigDecimal price, Colour colour, LocalDate date, Manufacturer manufacturer, String model) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        if (colour == null) {
            throw new IllegalArgumentException("Colour can't be null");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date can't be null");
        }
        if (manufacturer == null) {
            throw new IllegalArgumentException("Manufacturer can't be null or empty");
        }
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model can't be null or empty");
        }
    }
}
