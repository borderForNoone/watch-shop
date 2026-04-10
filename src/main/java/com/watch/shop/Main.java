package com.watch.shop;

import com.watch.shop.controller.WatchController;
import com.watch.shop.factory.WatchFactory;
import com.watch.shop.repository.WatchRepository;
import com.watch.shop.service.WatchService;
import com.watch.shop.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        WatchRepository repository = new WatchRepository();
        WatchService service = new WatchService(repository);
        ConsoleView view = new ConsoleView();
        WatchFactory factory = new WatchFactory();
        WatchController controller = new WatchController(service, view, factory);

        controller.run();
    }
}
