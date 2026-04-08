package com.watchshop.model;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Watch {
     protected double price;
     protected Colour colour;
     protected LocalDate storeArrivalDate;

    public Watch(double price, Colour colour, LocalDate storeArrivalDate) {
        if (price == 0) throw new IllegalArgumentException("Price can't be zero");
        if (price < 0) throw new IllegalArgumentException("Price can't be negative");
        if (colour == null) throw new IllegalArgumentException("Colour can't be null");
        if (storeArrivalDate == null) throw new IllegalArgumentException("Date can't be null");

        this.price = price;
        this.colour = colour;
        this.storeArrivalDate = storeArrivalDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public LocalDate getStoreArrivalDate() {
        return storeArrivalDate;
    }

    public void setStoreArrivalDate(LocalDate storeArrivalDate) {
        this.storeArrivalDate = storeArrivalDate;
    }

    @Override
    public String toString() {
        return String.format("[%s] Price: %.2f | Colour: %s | Arrival Date: %s",
                getClass().getSimpleName(), price, colour, storeArrivalDate);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Watch watch = (Watch) o;
        return Double.compare(price, watch.price) == 0 && colour == watch.colour && Objects.equals(storeArrivalDate, watch.storeArrivalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, colour, storeArrivalDate);
    }
}
