package com.watch.shop.view;

import com.watch.shop.model.Watch;
import com.watch.shop.model.enums.Colour;
import com.watch.shop.model.enums.WatchType;
import com.watch.shop.view.messages.Messages;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printWatches(List<Watch> watches) {
        if (watches.isEmpty()) {
            System.out.println(Messages.STORE_EMPTY);
        } else {
            watches.forEach(System.out::println);
        }
    }

    public void printMenu() {
        System.out.println(String.join("\n",
                "", Messages.MENU_HEADER,
                Messages.MENU_SHOW_ALL,
                Messages.MENU_ADD,
                Messages.MENU_SORT,
                Messages.MENU_TOTAL,
                Messages.MENU_EXIT,
                Messages.MENU_CHOICE
        ));
    }


    public void printSortMenu() {
        System.out.println(String.join("\n",
                Messages.SORT_HEADER,
                Messages.SORT_BY_PRICE,
                Messages.SORT_BY_COLOR,
                Messages.SORT_BY_DATE
        ));
    }

    public void printWatchTypeMenu(WatchType[] types) {
        System.out.println(Messages.PROMPT_WATCH_TYPE);
        Arrays.stream(types)
                .forEach(t -> System.out.printf("%s. %s%n", t.getCode(), t.name()));
    }

    public String readLine() {
        return scanner.nextLine().trim();
    }

    public String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            printMessage(Messages.ERR_EMPTY_FIELD);
        }
    }

    public <T extends Enum<T>> T readEnum(Class<T> enumClass, String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " " + Arrays.toString(enumClass.getEnumConstants()) + ": ");
                return Enum.valueOf(enumClass, scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                printMessage(Messages.ERR_INVALID_VALUE);
            }
        }
    }

    public BigDecimal readPrice() {
        while (true) {
            try {
                System.out.print(Messages.PROMPT_PRICE);
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                printMessage(Messages.ERR_NOT_A_NUMBER);
            }
        }
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                printMessage(Messages.ERR_NOT_AN_INT);
            }
        }
    }

    public boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            printMessage(Messages.ERR_BOOL_EXPECTED);
        }
    }

    public WatchType readWatchType(WatchType[] types) {
        while (true) {
            try {
                printWatchTypeMenu(types);
                return WatchType.fromCode(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                printMessage(Messages.ERR_UNKNOWN_TYPE);
            }
        }
    }

    public Colour readColour() {
        while (true) {
            try {
                System.out.print(Messages.PROMPT_COLOR + Arrays.toString(Colour.values()) + ": ");
                return Colour.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                printMessage(Messages.ERR_UNKNOWN_COLOR);
            }
        }
    }
}