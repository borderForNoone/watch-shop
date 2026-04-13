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
import com.watch.shop.view.WatchView;

import java.math.BigDecimal;
import java.util.List;

import static com.watch.shop.view.messages.Messages.PROMPT_BATTERY;
import static com.watch.shop.view.messages.Messages.PROMPT_CAPACITY;
import static com.watch.shop.view.messages.Messages.PROMPT_GPS;
import static com.watch.shop.view.messages.Messages.PROMPT_MECHANISM;
import static com.watch.shop.view.messages.Messages.PROMPT_OS;
import static com.watch.shop.view.messages.Messages.PROMPT_POWER_RES;
import static com.watch.shop.view.messages.Messages.TOTAL_PRICE;
import static com.watch.shop.view.messages.Messages.UNKNOWN_COMMAND;
import static com.watch.shop.view.messages.Messages.WATCH_ADDED;

public class WatchController {
    private final WatchService watchService;
    private final WatchView view;
    private final WatchFactory watchFactory;

    public WatchController(WatchService watchService, WatchView view, WatchFactory watchFactory) {
        this.watchService = watchService;
        this.view = view;
        this.watchFactory = watchFactory;
    }

    public void run() {
        while (true) {
            view.printMenu();
            if (!handleMenuChoice(view.readLine())) {
                break;
            }
        }
    }

    private boolean handleMenuChoice(String choice) {
        switch (choice) {
            case "1" -> view.printWatches(watchService.getAllWatches());
            case "2" -> addWatch();
            case "3" -> sortWatches();
            case "4" -> view.printMessage(
                    String.format(TOTAL_PRICE, watchService.getTotalPrice()));
            case "0" -> {
                return false;
            }
            default -> view.printMessage(UNKNOWN_COMMAND);
        }
        return true;
    }

    private void addWatch() {
        Watch watch = view.buildWatch(watchFactory);
        watchService.addWatch(watch);
        view.printMessage(WATCH_ADDED);
    }

    private Watch buildWatch(WatchType type, BigDecimal price,
                             Colour colour, Manufacturer manufacturer, String model) {
        return switch (type) {
            case SMART -> {
                OperatingSystem os = view.readEnum(OperatingSystem.class, PROMPT_OS);
                boolean hasGPS = view.readBoolean(PROMPT_GPS);
                yield watchFactory.createSmartWatch(price, colour, manufacturer, model, os, hasGPS);
            }
            case QUARTZ -> {
                BatteryType battery = view.readEnum(BatteryType.class, PROMPT_BATTERY);
                yield watchFactory.createQuartzWatch(price, colour, manufacturer, model, battery);
            }
            case MECHANICAL -> {
                int reserve = view.readInt(PROMPT_POWER_RES);
                MechanismType mech = view.readEnum(MechanismType.class, PROMPT_MECHANISM);
                yield watchFactory.createMechanicalWatch(price, colour, manufacturer, model, reserve, mech);
            }
            case SOLAR -> {
                int capacity = view.readInt(PROMPT_CAPACITY);
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
            default -> {
                view.printMessage(UNKNOWN_COMMAND);
                yield List.of();
            }
        };
        view.printWatches(sorted);
    }
}

