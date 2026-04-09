package com.watch.shop.factory;

import com.watch.shop.model.MechanicalWatch;
import com.watch.shop.model.QuartzWatch;
import com.watch.shop.model.SmartWatch;
import com.watch.shop.model.SolarWatch;
import com.watch.shop.model.Watch;
import com.watch.shop.model.enums.Colour;
import com.watch.shop.model.enums.WatchType;
import com.watch.shop.validation.WatchValidator;

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
        WatchValidator.validate(price, colour, LocalDate.now());

        return switch (type) {
            case QUARTZ -> new QuartzWatch(price, colour, LocalDate.now());
            case MECHANICAL -> new MechanicalWatch(price, colour, LocalDate.now());
            case SOLAR -> new SolarWatch(price, colour, LocalDate.now());
            case SMART -> new SmartWatch(price, colour, LocalDate.now());
        };
    }
}
