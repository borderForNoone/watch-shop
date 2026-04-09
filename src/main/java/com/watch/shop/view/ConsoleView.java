package com.watch.shop.view;

import com.watch.shop.controller.WatchController;
import com.watch.shop.factory.WatchFactory;
import com.watch.shop.model.Watch;
import com.watch.shop.model.enums.Colour;
import com.watch.shop.model.enums.WatchType;

import java.math.BigDecimal;
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

    private void print(Object obj) {
        if (obj instanceof List<?> list) {
            list.forEach(System.out::println);
        } else {
            System.out.println(obj);
        }
    }

    private String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            print("Помилка: поле не може бути порожнім");
        }
    }

    private WatchType readType() {
        while (true) {
            try {
                print("Оберіть тип годинника:");
                Arrays.stream(WatchType.values())
                        .forEach(type -> System.out.printf("%s. %s%n", type.getCode(), type.name()));
                String input = scanner.nextLine();
                return WatchType.fromCode(input);
            } catch (IllegalArgumentException e) {
                print("Помилка: невідомий тип, спробуйте ще раз");
            }
        }
    }

    private Colour readColour() {
        while (true) {
            try {
                System.out.print("Колір " + Arrays.toString(Colour.values()) + ": ");
                return Colour.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                print("Помилка: невідомий колір, спробуйте ще раз");
            }
        }
    }

    private BigDecimal readPrice() {
        while (true) {
            try {
                System.out.print("Ціна: ");
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                print("Помилка: введіть число");
            }
        }
    }

    public void addNewWatch() {
        while (true) {
            try {
                WatchType type = readType();
                Colour colour = readColour();
                BigDecimal price = readPrice();
                String manufacturer = readNonEmptyString("Введіть виробника: ");
                String model = readNonEmptyString("Введіть модель: ");

                Watch watch = watchFactory.create(type, price, colour, manufacturer, model);
                watchController.addWatch(watch);
                print("Годинник додано!");
                break;
            } catch (IllegalArgumentException e) {
                print("Помилка: " + e.getMessage());
            }
        }
    }

    private String readSortOption() {
        print("Сортувати за:\n1. Ціною\n2. Кольором\n3. Датою надходження\nВаш вибір: ");
        return scanner.nextLine();
    }

    public void printSortedWatches() {
        String choice = readSortOption();
        List<Watch> sorted = switch (choice) {
            case "1" -> watchController.sortByPrice();
            case "2" -> watchController.sortByColour();
            case "3" -> watchController.sortByArrivalDate();
            default -> {
                print("Невідома команда");
                yield List.of();
            }
        };
        print(sorted);
    }

    public void printTotalPrice() {
        print(String.format("Загальна вартість: %.2f", watchController.getTotalPrice()));
    }

    public void printOutAllWatches() {
        List<Watch> watches = watchController.getAllWatches();
        if (watches.isEmpty()) {
            print("Магазин порожній");
        } else {
            print(watches);
        }
    }

    public void start() {
        while (true) {
            printMenu();
            if (!handleMenuChoice(scanner.nextLine())) break;
        }
    }

    private void printMenu() {
        print("\n=== Магазин годинників ===\n" +
                "1. Показати всі годинники\n" +
                "2. Додати годинник\n" +
                "3. Сортувати годинники\n" +
                "4. Загальна вартість\n" +
                "0. Вийти\nВаш вибір: ");
    }

    private boolean handleMenuChoice(String choice) {
        return switch (choice) {
            case "1" -> {
                printOutAllWatches();
                yield true;
            }
            case "2" -> {
                addNewWatch();
                yield true;
            }
            case "3" -> {
                printSortedWatches();
                yield true;
            }
            case "4" -> {
                printTotalPrice();
                yield true;
            }
            case "0" -> false;
            default -> {
                print("Невідома команда");
                yield true;
            }
        };
    }
}