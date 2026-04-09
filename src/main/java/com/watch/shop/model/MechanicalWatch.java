package com.watch.shop.model;

public class MechanicalWatch extends Watch {

    private MechanicalWatch(MechanicalWatch.Builder builder) {
        super(builder);
    }

    public static class Builder extends Watch.Builder<MechanicalWatch.Builder> {
        @Override
        protected MechanicalWatch.Builder self() {
            return this;
        }

        @Override
        public MechanicalWatch build() {
            return new MechanicalWatch(this);
        }
    }
}
