package com.watch.shop.factory;

import com.watch.shop.model.MechanicalWatch;
import com.watch.shop.model.QuartzWatch;
import com.watch.shop.model.SmartWatch;
import com.watch.shop.model.SolarWatch;
import com.watch.shop.model.Watch;
import com.watch.shop.model.enums.BatteryType;
import com.watch.shop.model.enums.Colour;
import com.watch.shop.model.enums.Manufacturer;
import com.watch.shop.model.enums.MechanismType;
import com.watch.shop.model.enums.OperatingSystem;
import com.watch.shop.model.enums.WatchType;
import com.watch.shop.validation.WatchValidator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WatchFactory {

    public Watch create(WatchType type, BigDecimal price, Colour colour, Manufacturer manufacturer, String model) {
        return switch (type) {
            case QUARTZ -> createQuartzWatch(price, colour, manufacturer, model, BatteryType.LITHIUM);
            case MECHANICAL -> createMechanicalWatch(price, colour, manufacturer, model, 48, MechanismType.MANUAL);
            case SOLAR -> createSolarWatch(price, colour, manufacturer, model, 300);
            case SMART -> createSmartWatch(price, colour, manufacturer, model, OperatingSystem.OTHER, false);
        };
    }

    public Watch createQuartzWatch(BigDecimal price, Colour colour, Manufacturer manufacturer,
                                   String model, BatteryType batteryType) {

        WatchValidator.validate(price, colour, LocalDate.now(), manufacturer, model);

        return new QuartzWatch.Builder()
                .price(price)
                .colour(colour)
                .storeArrivalDate(LocalDate.now())
                .manufacturer(manufacturer)
                .model(model)
                .batteryType(batteryType)
                .build();
    }

    public Watch createMechanicalWatch(BigDecimal price, Colour colour, Manufacturer manufacturer,
                                       String model, int powerReserve, MechanismType mechanismType) {

        WatchValidator.validate(price, colour, LocalDate.now(), manufacturer, model);

        return new MechanicalWatch.Builder()
                .price(price)
                .colour(colour)
                .storeArrivalDate(LocalDate.now())
                .manufacturer(manufacturer)
                .model(model)
                .powerReserve(powerReserve)
                .mechanismType(mechanismType)
                .build();
    }

    public Watch createSolarWatch(BigDecimal price, Colour colour, Manufacturer manufacturer,
                                  String model, int batteryCapacity) {

        WatchValidator.validate(price, colour, LocalDate.now(), manufacturer, model);

        return new SolarWatch.Builder()
                .price(price)
                .colour(colour)
                .storeArrivalDate(LocalDate.now())
                .manufacturer(manufacturer)
                .model(model)
                .batteryCapacity(batteryCapacity)
                .build();
    }

    public Watch createSmartWatch(BigDecimal price, Colour colour, Manufacturer manufacturer,
                                  String model, OperatingSystem os, boolean hasGPS) {

        WatchValidator.validate(price, colour, LocalDate.now(), manufacturer, model);

        return new SmartWatch.Builder()
                .price(price)
                .colour(colour)
                .storeArrivalDate(LocalDate.now())
                .manufacturer(manufacturer)
                .model(model)
                .os(os)
                .hasGPS(hasGPS)
                .build();
    }
}