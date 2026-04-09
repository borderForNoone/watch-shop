package com.watch.shop.factory;

import com.watch.shop.model.MechanicalWatch;
import com.watch.shop.model.QuartzWatch;
import com.watch.shop.model.SmartWatch;
import com.watch.shop.model.SolarWatch;
import com.watch.shop.model.Watch;
import com.watch.shop.model.enums.Colour;
import com.watch.shop.model.enums.WatchType;
import com.watch.shop.validation.WatchValidator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WatchFactory {

    public Watch create(WatchType type, BigDecimal price, Colour colour, String manufacturer, String model) {
        WatchValidator.validate(price, colour, LocalDate.now(), manufacturer, model);

        return switch (type) {
            case QUARTZ -> new QuartzWatch.Builder()
                    .price(price)
                    .colour(colour)
                    .storeArrivalDate(LocalDate.now())
                    .manufacturer(manufacturer)
                    .model(model)
                    .build();
            case MECHANICAL -> new MechanicalWatch.Builder()
                    .price(price)
                    .colour(colour)
                    .storeArrivalDate(LocalDate.now())
                    .manufacturer(manufacturer)
                    .model(model)
                    .build();
            case SOLAR -> new SolarWatch.Builder()
                    .price(price)
                    .colour(colour)
                    .storeArrivalDate(LocalDate.now())
                    .manufacturer(manufacturer)
                    .model(model)
                    .build();
            case SMART -> new SmartWatch.Builder()
                    .price(price)
                    .colour(colour)
                    .storeArrivalDate(LocalDate.now())
                    .manufacturer(manufacturer)
                    .model(model)
                    .build();
        };
    }
}