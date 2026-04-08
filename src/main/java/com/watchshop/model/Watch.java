package com.watchshop.model;

import java.util.Date;
import java.util.Objects;

public abstract class Watch {
     protected double price;
     protected Colour colour;
     protected Date storeArrivalDate;

    public Watch(double price, Colour colour, Date storeArrivalDate) {
        this.price = price;
        this.colour = colour;
        this.storeArrivalDate = storeArrivalDate;
    }

    @Override
    public String toString() {
        return "Watch{" +
                "price=" + price +
                ", colour=" + colour +
                ", storeArrivalDate=" + storeArrivalDate +
                '}';
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
