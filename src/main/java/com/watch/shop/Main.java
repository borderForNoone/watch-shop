package com.watch.shop;

import com.watch.shop.controller.WatchController;
import com.watch.shop.factory.WatchFactory;
import com.watch.shop.repository.WatchRepository;
import com.watch.shop.service.WatchService;
import com.watch.shop.view.ConsoleView;
import com.watch.shop.view.WatchView;

public class Main {
    public static void main(String[] args) {
        WatchRepository repository = new WatchRepository();
        WatchFactory factory = new WatchFactory();
        WatchService service = new WatchService(repository);
        WatchView view = new ConsoleView();
        WatchController controller = new WatchController(service, view, factory);

        controller.run();
    }
}
