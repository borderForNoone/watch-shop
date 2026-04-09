package com.watch.shop.model;

public class QuartzWatch extends Watch {

    private QuartzWatch(Builder builder) {
        super(builder);
    }

    public static class Builder extends Watch.Builder<Builder> {
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
