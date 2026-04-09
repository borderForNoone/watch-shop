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

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        WatchRepository repository = new WatchRepository(new ArrayList<>());

        repository.addWatch(new QuartzWatch(1500.0, Colour.RED, LocalDate.of(2024, 1, 15)));
        repository.addWatch(new QuartzWatch(2200.0, Colour.BLUE, LocalDate.of(2024, 3, 10)));
        repository.addWatch(new MechanicalWatch(5000.0, Colour.BLACK, LocalDate.of(2023, 11, 5)));
        repository.addWatch(new SmartWatch(4500.0, Colour.BLACK, LocalDate.of(2024, 5, 18)));

        WatchFactory watchFactory = new WatchFactory();
        WatchService service = new WatchService(repository);
        WatchController controller = new WatchController(service);
        ConsoleView view = new ConsoleView(controller, watchFactory);

        view.start();
    }
}
