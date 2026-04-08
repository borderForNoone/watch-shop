package com.watchshop;

import com.watchshop.controller.WatchController;
import com.watchshop.model.Colour;
import com.watchshop.model.MechanicalWatch;
import com.watchshop.model.QuartzWatch;
import com.watchshop.model.SmartWatch;
import com.watchshop.repository.WatchRepository;
import com.watchshop.service.WatchService;
import com.watchshop.view.ConsoleView;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        WatchRepository repository = new WatchRepository(new ArrayList<>());

        repository.addWatch(new QuartzWatch(1500.0, Colour.RED, LocalDate.of(2024, 1, 15)));
        repository.addWatch(new QuartzWatch(2200.0, Colour.BLUE, LocalDate.of(2024, 3, 10)));
        repository.addWatch(new MechanicalWatch(5000.0, Colour.BLACK, LocalDate.of(2023, 11, 5)));
        repository.addWatch(new SmartWatch(4500.0, Colour.BLACK, LocalDate.of(2024, 5, 18)));

        WatchService service = new WatchService(repository);
        WatchController controller = new WatchController(service);
        ConsoleView view = new ConsoleView(controller);

        view.start();
    }
}
