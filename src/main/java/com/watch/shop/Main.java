package com.watch.shop;

import com.watch.shop.controller.WatchController;
import com.watch.shop.factory.WatchFactory;
import com.watch.shop.model.enums.Colour;
import com.watch.shop.model.MechanicalWatch;
import com.watch.shop.model.QuartzWatch;
import com.watch.shop.model.SmartWatch;
import com.watch.shop.repository.WatchRepository;
import com.watch.shop.service.WatchService;
import com.watch.shop.view.ConsoleView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        WatchRepository repository = new WatchRepository(new ArrayList<>());

        repository.addWatch(new QuartzWatch.Builder()
                .price(BigDecimal.valueOf(1500.0))
                .colour(Colour.RED)
                .storeArrivalDate(LocalDate.of(2024, 1, 15))
                .manufacturer("Omega")
                .model("1.0.1")
                .build());

        repository.addWatch(new QuartzWatch.Builder()
                .price(BigDecimal.valueOf(2200.0))
                .colour(Colour.BLUE)
                .storeArrivalDate(LocalDate.of(2024, 3, 10))
                .manufacturer("Omega")
                .model("1.0.1")
                .build());

        repository.addWatch(new MechanicalWatch.Builder()
                .price(BigDecimal.valueOf(5000.0))
                .colour(Colour.BLACK)
                .storeArrivalDate(LocalDate.of(2023, 11, 5))
                .manufacturer("Omega")
                .model("1.0.1")
                .build());

        repository.addWatch(new SmartWatch.Builder()
                .price(BigDecimal.valueOf(4500.0))
                .colour(Colour.BLACK)
                .storeArrivalDate(LocalDate.of(2024, 5, 18))
                .manufacturer("Omega")
                .model("1.0.1")
                .build());

        WatchFactory watchFactory = new WatchFactory();
        WatchService service = new WatchService(repository);
        WatchController controller = new WatchController(service);
        ConsoleView view = new ConsoleView(controller, watchFactory);

        view.start();
    }
}
