package com.watch.shop.model;

import com.watch.shop.model.enums.Colour;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Watch {
    protected BigDecimal price;
    protected Colour colour;
    protected LocalDate storeArrivalDate;
    private final String manufacturer;
    private final String model;

    protected Watch(Builder<?> builder) {
        this.price = builder.price;
        this.colour = builder.colour;
        this.storeArrivalDate = builder.storeArrivalDate;
        this.manufacturer = builder.manufacturer;
        this.model = builder.model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Colour getColour() {
        return colour;
    }

    public LocalDate getStoreArrivalDate() {
        return storeArrivalDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return String.format("[%s] Price: %.2f | Colour: %s | Arrival Date: %s | Manufacturer: %s | Model: %s",
                getClass().getSimpleName(), price, colour, storeArrivalDate, manufacturer, model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Watch watch = (Watch) o;

        return (price == null ? watch.price == null : price.compareTo(watch.price) == 0)
                && colour == watch.colour
                && Objects.equals(storeArrivalDate, watch.storeArrivalDate)
                && Objects.equals(manufacturer, watch.manufacturer)
                && Objects.equals(model, watch.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, colour, storeArrivalDate, manufacturer, model);
    }

    public static abstract class Builder<T extends Builder<T>> {
        private BigDecimal price;
        private Colour colour;
        private LocalDate storeArrivalDate;
        private String manufacturer;
        private String model;

        public T price(BigDecimal price) {
            this.price = price;
            return self();
        }

        public T colour(Colour colour) {
            this.colour = colour;
            return self();
        }

        public T storeArrivalDate(LocalDate date) {
            this.storeArrivalDate = date;
            return self();
        }

        public T manufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return self();
        }

        public T model(String model) {
            this.model = model;
            return self();
        }

        protected abstract T self();

        public abstract Watch build();
    }
}
