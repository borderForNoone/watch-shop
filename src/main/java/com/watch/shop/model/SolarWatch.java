package com.watch.shop.model;

import com.watch.shop.model.enums.Colour;

import java.time.LocalDate;

public class SolarWatch extends Watch {

    private SolarWatch(SolarWatch.Builder builder) {
        super(builder);
    }

    public static class Builder extends Watch.Builder<SolarWatch.Builder> {
        @Override
        protected SolarWatch.Builder self() {
            return this;
        }

        @Override
        public SolarWatch build() {
            return new SolarWatch(this);
        }
    }
}
