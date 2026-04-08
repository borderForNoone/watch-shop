package com.watchshop.service;

import com.watchshop.model.Watch;
import com.watchshop.repository.WatchRepository;

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


}
