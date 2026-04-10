package com.watch.shop.data;

import com.watch.shop.model.MechanicalWatch;
import com.watch.shop.model.QuartzWatch;
import com.watch.shop.model.SmartWatch;
import com.watch.shop.model.Watch;
import com.watch.shop.model.enums.BatteryType;
import com.watch.shop.model.enums.Colour;
import com.watch.shop.model.enums.Manufacturer;
import com.watch.shop.model.enums.MechanismType;
import com.watch.shop.model.enums.OperatingSystem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WatchTestDataGenerator {
    public List<Watch> retrieveGeneratedData() {
        List<Watch> watches = new ArrayList<>();

        watches.add(createQuartzWatch1());
        watches.add(createQuartzWatch2());
        watches.add(createMechanicalWatch());
        watches.add(createSmartWatch());

        return watches;
    }

    private Watch createQuartzWatch1() {
        return new QuartzWatch.Builder()
                .price(BigDecimal.valueOf(1500.0))
                .colour(Colour.RED)
                .storeArrivalDate(LocalDate.of(2024, 1, 15))
                .manufacturer(Manufacturer.OMEGA)
                .model("1.0.1")
                .batteryType(BatteryType.LITHIUM)
                .build();
    }

    private Watch createQuartzWatch2() {
        return new QuartzWatch.Builder()
                .price(BigDecimal.valueOf(2200.0))
                .colour(Colour.BLUE)
                .storeArrivalDate(LocalDate.of(2024, 3, 10))
                .manufacturer(Manufacturer.OMEGA)
                .model("1.0.2")
                .batteryType(BatteryType.SILVER_OXIDE)
                .build();
    }

    private Watch createMechanicalWatch() {
        return new MechanicalWatch.Builder()
                .price(BigDecimal.valueOf(5000.0))
                .colour(Colour.BLACK)
                .storeArrivalDate(LocalDate.of(2023, 11, 5))
                .manufacturer(Manufacturer.OMEGA)
                .model("2.0.0")
                .powerReserve(72)
                .mechanismType(MechanismType.AUTOMATIC)
                .build();
    }

    private Watch createSmartWatch() {
        return new SmartWatch.Builder()
                .price(BigDecimal.valueOf(4500.0))
                .colour(Colour.BLACK)
                .storeArrivalDate(LocalDate.of(2024, 5, 18))
                .manufacturer(Manufacturer.OMEGA)
                .model("3.0.0")
                .os(OperatingSystem.WATCH_OS)
                .hasGPS(true)
                .build();
    }
}
