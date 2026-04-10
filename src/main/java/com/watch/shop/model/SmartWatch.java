package com.watch.shop.model;

import com.watch.shop.model.enums.OperatingSystem;

public class SmartWatch extends Watch {
    private final OperatingSystem os;
    private final boolean hasGPS;

    private SmartWatch(Builder builder) {
        super(builder);
        this.os = builder.os;
        this.hasGPS = builder.hasGPS;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" | OS: %s | GPS: %s", os, hasGPS ? "Yes" : "No");
    }

    public static class Builder extends Watch.Builder<Builder> {
        private OperatingSystem os;
        private boolean hasGPS;

        public Builder os(OperatingSystem os) {
            this.os = os;
            return this;
        }

        public Builder hasGPS(boolean hasGPS) {
            this.hasGPS = hasGPS;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public SmartWatch build() {
            return new SmartWatch(this);
        }
    }
}
