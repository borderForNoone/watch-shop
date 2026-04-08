package com.watchshop.repository;

import com.watchshop.model.Watch;

import java.util.List;

public class WatchRepository {
    private final List<Watch> watches;

    public WatchRepository(List<Watch> watches) {
        this.watches = watches;
    }

    public void addWatch(Watch watch) {
        watches.add(watch);
    }

    public void removeWatch(Watch watch) {
        watches.remove(watch);
    }

    public List<Watch> getAll() {
        return watches;
    }

    @Override
    public String toString() {
        return "WatchRepository{" +
                "watches=" + watches +
                '}';
    }
}
