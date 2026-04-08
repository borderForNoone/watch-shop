package com.watchshop.view;

import com.watchshop.controller.WatchController;
import com.watchshop.model.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private WatchController watchController;
    private Scanner scanner = new Scanner(System.in);

    public ConsoleView(WatchController watchController) {
        this.watchController = watchController;
    }

    public void printOutAllWatches() {
        List<Watch> watches = watchController.getAllWatches();

        if (watches.isEmpty()) {
            System.out.println("Магазин порожній");
            return;
        }

        for (Watch watch : watches) {
            System.out.println(watch);
        }
    }

    public void addNewWatch() {
        String type = readType();
        Colour colour = readColour();
        double price = readPrice();

        try {
            Watch watch = switch (type) {
                case "1" -> new QuartzWatch(price, colour, LocalDate.now());
                case "2" -> new MechanicalWatch(price, colour, LocalDate.now());
                case "3" -> new SolarWatch(price, colour, LocalDate.now());
                case "4" -> new SmartWatch(price, colour, LocalDate.now());
                default  -> throw new IllegalArgumentException("Unknown type");
            };

            watchController.addWatch(watch);
            System.out.println("Годинник додано!");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
            addNewWatch(); // перепитуємо
        }
    }

    private String readType() {
        while (true) {
            System.out.println("Оберіть тип годинника:");
            System.out.println("1. Кварцовий");
            System.out.println("2. Механічний");
            System.out.println("3. Сонячний");
            System.out.println("4. Смарт");
            System.out.print("Ваш вибір: ");
            String type = scanner.nextLine();
            if (List.of("1", "2", "3", "4").contains(type)) return type;
            System.out.println("Помилка: невідомий тип, спробуйте ще раз");
        }
    }

    private Colour readColour() {
        while (true) {
            try {
                System.out.print("Колір " + Arrays.toString(Colour.values()) + ": ");
                return Colour.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: невідомий колір, спробуйте ще раз");
            }
        }
    }

    private double readPrice() {
        while (true) {
            try {
                System.out.print("Ціна: ");
                double price = Double.parseDouble(scanner.nextLine());
                return price;
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть число");
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }

    public void printSortedWatches() {
        System.out.println("Сортувати за:");
        System.out.println("1. Ціною");
        System.out.println("2. Кольором");
        System.out.println("3. Датою надходження");
        System.out.print("Ваш вибір: ");
        String choice = scanner.nextLine();

        List<Watch> sorted = switch (choice) {
            case "1" -> watchController.sortByPrice();
            case "2" -> watchController.sortByColour();
            case "3" -> watchController.sortByArrivalDate();
            default  -> { System.out.println("Невідома команда"); yield List.of(); }
        };

        sorted.forEach(System.out::println);
    }

    public void printTotalPrice() {
        System.out.printf("Загальна вартість: %.2f%n", watchController.getTotalPrice());
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Магазин годинників ===");
            System.out.println("1. Показати всі годинники");
            System.out.println("2. Додати годинник");
            System.out.println("3. Сортувати годинники");
            System.out.println("4. Загальна вартість");
            System.out.println("0. Вийти");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> printOutAllWatches();
                case "2" -> addNewWatch();
                case "3" -> printSortedWatches();
                case "4" -> printTotalPrice();
                case "0" -> running = false;
                default  -> System.out.println("Невідома команда");
            }
        }
    }
}
