package com.watch.shop.view;

import com.watch.shop.factory.WatchFactory;
import com.watch.shop.model.Watch;
import com.watch.shop.model.enums.Colour;
import com.watch.shop.model.enums.WatchType;

import java.math.BigDecimal;
import java.util.List;

public interface WatchView {
    void printMessage(String message);
    void printWatches(List<Watch> watches);
    void printMenu();
    void printSortMenu();
    void printWatchTypeMenu(WatchType[] types);

    String readLine();
    Watch buildWatch(WatchFactory factory);
    String readNonEmptyString(String prompt);
    <T extends Enum<T>> T readEnum(Class<T> enumClass, String prompt);
    BigDecimal readPrice();
    int readInt(String prompt);
    boolean readBoolean(String prompt);
    WatchType readWatchType(WatchType[] types);
    Colour readColour();
}
