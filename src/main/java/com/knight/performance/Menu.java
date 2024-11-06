package com.knight.performance;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

class Menu {
    private static final Logger logger = Logger.getLogger(Menu.class.getName()); // Створення логера
    private Command[] commands;  // Масив команд
    private Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
        this.commands = new Command[11];
        logger.info("Menu initialized"); // Логування ініціалізації меню
    }

    // Додавання команд
    public void addCommand(int option, Command command) {
        if (option > 0 && option < commands.length) {
            commands[option] = command;
            logger.info("Command added at option: " + option); // Логування додавання команди
        } else {
            logger.warning("Invalid option for adding command: " + option); // Логування неправильного вибору
        }
    }

    public void run() {
        logger.info("Menu started"); // Логування запуску меню
        while (true) {
            System.out.println("\n--- Knight's Armory Menu ---");
            System.out.println("1. Add Armor");
            System.out.println("2. Show Armory");
            System.out.println("3. Calculate Total Price");
            System.out.println("4. Sort by Weight");
            System.out.println("5. Find by Price Range");
            System.out.println("6. Save Armory to File");
            System.out.println("7. Load Armory from File");
            System.out.println("8. Help");
            System.out.println("9. Remove Armor by Name and Price");
            System.out.println("10. Exit");

            int choice = 0;
            try {
                do {
                    System.out.print("Choose an option: ");
                    choice = scanner.nextInt();
                } while (choice < 1 || choice > 10);

                logger.info("User selected option: " + choice); // Логування вибору користувача

                Command command = commands[choice];
                if (command != null) {
                    command.execute();
                    logger.info("Command executed for option: " + choice); // Логування виконання команди
                } else {
                    logger.warning("No command found for option: " + choice); // Логування відсутньої команди
                }

                if (choice == 10) { // Вихід з меню
                    logger.info("Exiting menu");
                    break;
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Exception occurred: ", e); // Логування виняткової ситуації
                scanner.nextLine(); // Очищення буфера сканера
            }
        }
    }
}
