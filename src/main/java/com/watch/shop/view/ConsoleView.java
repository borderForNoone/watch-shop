package com.watch.shop.view;

import com.watch.shop.controller.WatchController;
import com.watch.shop.factory.WatchFactory;
import com.watch.shop.model.Watch;
import com.watch.shop.model.enums.Colour;
import com.watch.shop.model.enums.WatchType;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final WatchController watchController;
    private final Scanner scanner = new Scanner(System.in);
    private final WatchFactory watchFactory;

    public ConsoleView(WatchController watchController, WatchFactory watchFactory) {
        this.watchController = watchController;
        this.watchFactory = watchFactory;
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

    private String readManufacturer() {
        while (true) {
            System.out.print("Введіть виробника: ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Помилка: виробник не може бути порожнім");
        }
    }

    private String readModel() {
        while (true) {
            System.out.print("Введіть модель: ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Помилка: модель не може бути порожньою");
        }
    }

    public void addNewWatch() {
        while (true) {
            try {
                WatchType type = readType();
                Colour colour = readColour();
                double price = readPrice();
                String manufacturer = readManufacturer();
                String model = readModel();

                Watch watch = watchFactory.create(type, price, colour, manufacturer, model);

                watchController.addWatch(watch);
                System.out.println("Годинник додано!");
                break;

            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }

    private WatchType readType() {
        while (true) {
            try {
                System.out.println("Оберіть тип годинника:");
                Arrays.stream(WatchType.values())
                        .forEach(type -> System.out.printf("%s. %s%n", type.getCode(), type.name()));

                System.out.print("Ваш вибір: ");
                String input = scanner.nextLine();

                return WatchType.fromCode(input);

            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: невідомий тип, спробуйте ще раз");
            }
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

    private String readSortOption() {
        System.out.println("Сортувати за:");
        System.out.println("1. Ціною");
        System.out.println("2. Кольором");
        System.out.println("3. Датою надходження");
        System.out.print("Ваш вибір: ");

        return scanner.nextLine();
    }

    public void printSortedWatches() {
        String choice = readSortOption();
        List<Watch> sorted = getSortedWatches(choice);
        sorted.forEach(System.out::println);
    }

    private List<Watch> getSortedWatches(String choice) {
        return switch (choice) {
            case "1" -> watchController.sortByPrice();
            case "2" -> watchController.sortByColour();
            case "3" -> watchController.sortByArrivalDate();
            default -> {
                System.out.println("Невідома команда");
                yield List.of();
            }
        };
    }

    public void printTotalPrice() {
        System.out.printf("Загальна вартість: %.2f%n", watchController.getTotalPrice());
    }

    public void start() {
        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            if (!handleMenuChoice(choice)) break;
        }
    }

    private void printMenu() {
        System.out.println("\n=== Магазин годинників ===");
        System.out.println("1. Показати всі годинники");
        System.out.println("2. Додати годинник");
        System.out.println("3. Сортувати годинники");
        System.out.println("4. Загальна вартість");
        System.out.println("0. Вийти");
        System.out.print("Ваш вибір: ");
    }

    private boolean handleMenuChoice(String choice) {
        switch (choice) {
            case "1" -> printOutAllWatches();
            case "2" -> addNewWatch();
            case "3" -> printSortedWatches();
            case "4" -> printTotalPrice();
            case "0" -> {
                return false;
            }
            default -> System.out.println("Невідома команда");
        }
        return true;
    }
}
