package com.watch.shop.model;

import com.watch.shop.model.enums.Colour;

import java.time.LocalDate;

public class SmartWatch extends Watch {

    public SmartWatch(double price, Colour colour, LocalDate storeArrivalDate) {
        super(price, colour, storeArrivalDate);
    }
}
