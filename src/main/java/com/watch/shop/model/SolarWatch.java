package com.watch.shop.model;

public class SolarWatch extends Watch {
    private final int batteryCapacity;

    private SolarWatch(Builder builder) {
        super(builder);
        this.batteryCapacity = builder.batteryCapacity;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" | Battery Capacity: %dmAh", batteryCapacity);
    }

    public static class Builder extends Watch.Builder<Builder> {
        private int batteryCapacity;

        public Builder batteryCapacity(int batteryCapacity) {
            this.batteryCapacity = batteryCapacity;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public SolarWatch build() {
            return new SolarWatch(this);
        }
    }
}
