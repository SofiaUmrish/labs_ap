package com.knight.performance;
import java.util.Scanner;

class Menu {
    private Command[] commands;  //масив команд
    private Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
        this.commands = new Command[12];
    }

    public Command[] getCommands() {
        return commands;
    }

    //додавання команд
    public void addCommand(int option, Command command) {
        if (option > 0 && option < commands.length) {
            commands[option] = command;
        }
    }



    public void run() {
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
            do {
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();
            } while (choice < 1 || choice > 10);

            Command command = commands[choice];
            if (command != null) {
                command.execute();
            }
        }
    }
}
