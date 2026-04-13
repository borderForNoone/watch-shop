package com.watch.shop.model;

import com.watch.shop.model.enums.BatteryType;

public class QuartzWatch extends Watch {
    private final BatteryType batteryType;

    private QuartzWatch(Builder builder) {
        super(builder);
        this.batteryType = builder.batteryType;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" | Battery Type: %s", batteryType);
    }

    public static class Builder extends Watch.Builder<Builder> {
        private BatteryType batteryType;

        public Builder batteryType(BatteryType batteryType) {
            this.batteryType = batteryType;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public QuartzWatch build() {
            return new QuartzWatch(this);
        }
    }
}
