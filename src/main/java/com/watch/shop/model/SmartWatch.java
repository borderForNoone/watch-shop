package com.watch.shop.model;

public class SmartWatch extends Watch {

    private SmartWatch(SmartWatch.Builder builder) {
        super(builder);
    }

    public static class Builder extends Watch.Builder<SmartWatch.Builder> {
        @Override
        protected SmartWatch.Builder self() {
            return this;
        }

        @Override
        public SmartWatch build() {
            return new SmartWatch(this);
        }
    }
}
