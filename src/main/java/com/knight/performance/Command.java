package com.knight.performance;
import com.knight.armor_actions.AactionsOnArmor;
import java.util.Scanner;

public interface Command {
    void execute();//виконання команди
}

class AddArmorCommand implements Command {
    private AactionsOnArmor actions;
    private Scanner scanner;

    public AddArmorCommand(AactionsOnArmor actions, Scanner scanner) {
        this.actions = actions;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("\nEnter the amount of armor you would like to add: ");
        int numb = scanner.nextInt();
        do {
            actions.addArmor(scanner);
            numb--;
        } while (numb > 0);
    }
}

class SortArmorByWeightCommand implements Command {
    private AactionsOnArmor actions;

    public SortArmorByWeightCommand(AactionsOnArmor actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.sortArmorByWeight();
        System.out.println("\nSorted by weight.\n");
        actions.showArmory();
    }
}

class SaveArmoryToFileCommand implements Command {
    private AactionsOnArmor actions;

    public SaveArmoryToFileCommand(AactionsOnArmor actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.saveArmoryToFile("armory.txt");
        System.out.println("\nArmory saved to file.\n");
    }
}

class ShowArmoryCommand implements Command {
    private AactionsOnArmor actions;

    public ShowArmoryCommand(AactionsOnArmor actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.showArmory();
    }
}

class CalculateTotalPriceCommand implements Command {
    private AactionsOnArmor actions;

    public CalculateTotalPriceCommand(AactionsOnArmor actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        System.out.println("\nTotal Price: $" + actions.calculateTotalPrice());
    }
}

class FindArmorByPriceCommand implements Command {
    private AactionsOnArmor actions;
    private Scanner scanner;

    public FindArmorByPriceCommand(AactionsOnArmor actions, Scanner scanner) {
        this.actions = actions;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        actions.findArmorByPrice(scanner);
    }
}

class RemoveArmorByNameAndPriceCommand implements Command {
    private AactionsOnArmor actions;
    private Scanner scanner;

    public RemoveArmorByNameAndPriceCommand(AactionsOnArmor actions, Scanner scanner) {
        this.actions = actions;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        actions.removeArmorByNameAndPrice(scanner);
    }
}

class LoadArmoryFromFileCommand implements Command {
    private AactionsOnArmor actions;

    public LoadArmoryFromFileCommand(AactionsOnArmor actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.loadArmoryFromFile("armory.txt");
        System.out.println("\nArmory loaded from file.\n");
    }
}

class HelpCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\n--- Help ---");
        System.out.println("1. Add Armor - Add a new piece of armor to the knight's armory.");
        System.out.println("2. Show Armory - Display all armor in the knight's armory.");
        System.out.println("3. Calculate Total Price - Calculate the total price of all armor.");
        System.out.println("4. Sort Armory by Weight - Sort all armor by increasing weight.");
        System.out.println("5. Find Armory by Price Range - Search for armor within a specified price range.");
        System.out.println("6. Save Armory to File - Save the current armory to a file.");
        System.out.println("7. Load Armory from File - Load an armory from a file.");
        System.out.println("8. Remove Armor by Name and Price - Remove an armor item by specifying its name and price.");
        System.out.println("9. Exit - Exit the application.\n");
    }
}

class ExitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Exiting...");
        System.exit(0);
    }
}
