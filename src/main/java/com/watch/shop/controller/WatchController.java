package com.watch.shop.controller;

import com.watch.shop.factory.WatchFactory;
import com.watch.shop.model.Watch;
import com.watch.shop.model.enums.BatteryType;
import com.watch.shop.model.enums.Colour;
import com.watch.shop.model.enums.Manufacturer;
import com.watch.shop.model.enums.MechanismType;
import com.watch.shop.model.enums.OperatingSystem;
import com.watch.shop.model.enums.WatchType;
import com.watch.shop.service.WatchService;
import com.watch.shop.view.ConsoleView;

import java.math.BigDecimal;
import java.util.List;

public class WatchController {
    private final WatchService watchService;
    private final ConsoleView view;
    private final WatchFactory watchFactory;

    public WatchController(WatchService watchService, ConsoleView view, WatchFactory watchFactory) {
        this.watchService = watchService;
        this.view = view;
        this.watchFactory = watchFactory;
    }

    public void run() {
        while (true) {
            view.printMenu();
            if (!handleMenuChoice(view.readLine())) break;
        }
    }

    private boolean handleMenuChoice(String choice) {
        switch (choice) {
            case "1" -> view.printWatches(watchService.getAllWatches());
            case "2" -> addWatch();
            case "3" -> sortWatches();
            case "4" -> view.printMessage(
                    String.format("Total price: %.2f", watchService.getTotalPrice()));
            case "0" -> { return false; }
            default  -> view.printMessage("Unknown command");
        }
        return true;
    }

    private void addWatch() {
        while (true) {
            try {
                WatchType type         = view.readWatchType(WatchType.values());
                Colour colour          = view.readColour();
                BigDecimal price       = view.readPrice();
                Manufacturer manufacturer = view.readEnum(Manufacturer.class, "Manufacturer");
                String model           = view.readNonEmptyString("Enter model: ");

                Watch watch = buildWatch(type, price, colour, manufacturer, model);
                watchService.addWatch(watch);
                view.printMessage("Watch added successfully!");
                break;

            } catch (Exception e) {
                view.printMessage("Error: " + e.getMessage());
            }
        }
    }

    private Watch buildWatch(WatchType type, BigDecimal price,
                             Colour colour, Manufacturer manufacturer, String model) {
        return switch (type) {
            case SMART -> {
                OperatingSystem os = view.readEnum(OperatingSystem.class, "Operating System");
                boolean hasGPS = view.readBoolean("GPS (true/false): ");
                yield watchFactory.createSmartWatch(price, colour, manufacturer, model, os, hasGPS);
            }
            case QUARTZ -> {
                BatteryType battery = view.readEnum(BatteryType.class, "Battery type");
                yield watchFactory.createQuartzWatch(price, colour, manufacturer, model, battery);
            }
            case MECHANICAL -> {
                int reserve = view.readInt("Power reserve (hours): ");
                MechanismType mech  = view.readEnum(MechanismType.class, "Mechanism type");
                yield watchFactory.createMechanicalWatch(price, colour, manufacturer, model, reserve, mech);
            }
            case SOLAR -> {
                int capacity = view.readInt("Battery capacity (mAh): ");
                yield watchFactory.createSolarWatch(price, colour, manufacturer, model, capacity);
            }
        };
    }

    private void sortWatches() {
        view.printSortMenu();
        List<Watch> sorted = switch (view.readLine()) {
            case "1" -> watchService.sortByPrice();
            case "2" -> watchService.sortByColour();
            case "3" -> watchService.sortByArrivalDate();
            default  -> { view.printMessage("Unknown command"); yield List.of(); }
        };
        view.printWatches(sorted);
    }
}

