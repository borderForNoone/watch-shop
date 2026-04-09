package com.watch.shop.controller;

import com.watch.shop.model.Watch;
import com.watch.shop.service.WatchService;

import java.util.List;

public class WatchController {
    private WatchService watchService;

    public WatchController(WatchService watchService) {
        this.watchService = watchService;
    }

    public List<Watch> getAllWatches() {
        return watchService.getAllWatches();
    }

    public void addWatch(Watch watch) {
        watchService.addWatch(watch);
    }

    public List<Watch> sortAlphabetically() {
        return watchService.sortAlphabetically();
    }

    public List<Watch> sortByArrivalDate() {
        return watchService.sortByArrivalDate();
    }

    public List<Watch> sortByArrivalDateDescending() {
        return watchService.sortByArrivalDateDescending();
    }

    public List<Watch> sortByPriceDescending() {
        return watchService.sortByPriceDescending();
    }

    public List<Watch> sortByPrice() {
        return watchService.sortByPrice();
    }

    public List<Watch> sortByColour() {
        return watchService.sortByColour();
    }

    public double getTotalPrice() {
        return watchService.getTotalPrice();
    }
}
