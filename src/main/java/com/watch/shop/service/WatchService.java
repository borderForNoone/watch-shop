package com.watch.shop.service;

import com.watch.shop.model.Watch;
import com.watch.shop.repository.WatchRepository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class WatchService {
    private final WatchRepository watchRepository;

    public WatchService(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    public List<Watch> sortAlphabetically() {
        return watchRepository.getAll().stream()
                .sorted()
                .toList();
    }

    public List<Watch> sortByPrice() {
        return watchRepository.getAll().stream()
                .sorted(Comparator.comparing(Watch::getPrice))
                .toList();
    }

    public List<Watch> sortByPriceDescending() {
        return watchRepository.getAll().stream()
                .sorted(Comparator.comparing(Watch::getPrice).reversed())
                .toList();
    }

    public List<Watch> sortByColour() {
        return watchRepository.getAll().stream()
                .sorted(Comparator.comparing(w -> w.getColour().name()))
                .toList();
    }

    public List<Watch> sortByArrivalDate() {
        return watchRepository.getAll().stream()
                .sorted(Comparator.comparing(Watch::getStoreArrivalDate))
                .toList();
    }

    public List<Watch> sortByArrivalDateDescending() {
        return watchRepository.getAll().stream()
                .sorted(Comparator.comparing(Watch::getStoreArrivalDate).reversed())
                .toList();
    }

    public List<Watch> getAllWatches() {
        return List.copyOf(watchRepository.getAll());
    }

    public void addWatch(Watch watch) {
        watchRepository.addWatch(watch);
    }

    public BigDecimal getTotalPrice() {
        return watchRepository.getAll().stream()
                .map(Watch::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
