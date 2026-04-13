package com.watch.shop.model;

import com.watch.shop.model.enums.MechanismType;

public class MechanicalWatch extends Watch {
    private final int powerReserve;
    private final MechanismType mechanismType;

    private MechanicalWatch(Builder builder) {
        super(builder);
        this.powerReserve = builder.powerReserve;
        this.mechanismType = builder.mechanismType;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" | Power Reserve: %d hours | Mechanism: %s",
                        powerReserve, mechanismType);
    }

    public static class Builder extends Watch.Builder<Builder> {
        private int powerReserve;
        private MechanismType mechanismType;

        public Builder powerReserve(int powerReserve) {
            this.powerReserve = powerReserve;
            return this;
        }

        public Builder mechanismType(MechanismType mechanismType) {
            this.mechanismType = mechanismType;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public MechanicalWatch build() {
            return new MechanicalWatch(this);
        }
    }
}
