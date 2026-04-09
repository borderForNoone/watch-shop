package com.watch.shop.factory;

import com.watch.shop.model.*;
import com.watch.shop.model.enums.Colour;
import com.watch.shop.model.enums.WatchType;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class WatchFactory {

    private final Map<String, BiFunction<Double, Colour, Watch>> creators = new HashMap<>();

    public WatchFactory() {
        creators.put("1", (price, colour) -> new QuartzWatch(price, colour, LocalDate.now()));
        creators.put("2", (price, colour) -> new MechanicalWatch(price, colour, LocalDate.now()));
        creators.put("3", (price, colour) -> new SolarWatch(price, colour, LocalDate.now()));
        creators.put("4", (price, colour) -> new SmartWatch(price, colour, LocalDate.now()));
    }

    public Watch create(WatchType type, double price, Colour colour) {
        return switch (type) {
            case QUARTZ -> new QuartzWatch(price, colour, LocalDate.now());
            case MECHANICAL -> new MechanicalWatch(price, colour, LocalDate.now());
            case SOLAR -> new SolarWatch(price, colour, LocalDate.now());
            case SMART -> new SmartWatch(price, colour, LocalDate.now());
        };
    }
}
