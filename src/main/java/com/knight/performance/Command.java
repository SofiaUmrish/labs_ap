package com.knight.performance;

import com.knight.armor_actions.AactionsOnArmor;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.InputMismatchException;
public interface Command {
    void execute(); // Виконання команди
}

class AddArmorCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddArmorCommand.class);
    private AactionsOnArmor actions;
    private Scanner scanner;

    public AddArmorCommand(AactionsOnArmor actions, Scanner scanner) {
        this.actions = actions;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        logger.info("Executing AddArmorCommand");
        try {
            System.out.println("\nEnter the amount of armor you would like to add: ");
            int numb = scanner.nextInt();
            do {
                actions.addArmor(scanner);
                numb--;
            } while (numb > 0);
            logger.info("Armor added successfully");
        } catch (InputMismatchException e) {
            logger.fatal("Critical error: Non-numeric input entered instead of a number", e);
            System.err.println("Critical error: You must enter a number. Exiting command.");
        } catch (Exception e) {
            logger.error("Error while adding armor", e);
        } finally {
            scanner.nextLine(); // Очищення буфера сканера
        }
    }

}

class SortArmorByWeightCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SortArmorByWeightCommand.class);
    private AactionsOnArmor actions;
    private Scanner scanner;

    public SortArmorByWeightCommand(AactionsOnArmor actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        logger.info("Executing SortArmorByWeightCommand");
        try {
            actions.sortArmorByWeight();
            System.out.println("\nSorted by weight.\n");
            actions.showArmory();
            logger.info("Armor sorted by weight");
        } catch (InputMismatchException e) {
            logger.fatal("Critical error: Non-numeric input entered instead of a number", e);
            System.err.println("Critical error: You must enter a number. Exiting command.");
        } catch (Exception e) {
            logger.error("Error while adding armor", e);
        }finally {
            scanner.nextLine(); // Очищення буфера сканера
        }
    }
}

class SaveArmoryToFileCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SaveArmoryToFileCommand.class);
    private AactionsOnArmor actions;
    private Scanner scanner;

    public SaveArmoryToFileCommand(AactionsOnArmor actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        logger.info("Executing SaveArmoryToFileCommand");
        try {
            actions.saveArmoryToFile("armory.txt");
            System.out.println("\nArmory saved to file.\n");
            logger.info("Armory saved to file successfully");
        } catch (InputMismatchException e) {
            logger.fatal("Critical error: Non-numeric input entered instead of a number", e);
            System.err.println("Critical error: You must enter a number. Exiting command.");
        } catch (Exception e) {
            logger.error("Error while adding armor", e);
        }finally {
            scanner.nextLine(); // Очищення буфера сканера
        }
    }
}

class ShowArmoryCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowArmoryCommand.class);
    private AactionsOnArmor actions;

    public ShowArmoryCommand(AactionsOnArmor actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        logger.info("Executing ShowArmoryCommand");
        try {
            actions.showArmory();
            logger.info("Armory displayed successfully");
        } catch (Exception e) {
            logger.fatal("Critical error while sorting armor by weight", e);
            System.err.println("Critical error occurred. Please check email for details.");
        }
    }
}

class CalculateTotalPriceCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CalculateTotalPriceCommand.class);
    private AactionsOnArmor actions;

    public CalculateTotalPriceCommand(AactionsOnArmor actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        logger.info("Executing CalculateTotalPriceCommand");
        try {
            System.out.println("\nTotal Price: $" + actions.calculateTotalPrice());
            logger.info("Total price calculated successfully");
        } catch (Exception e) {
            logger.fatal("Critical error while sorting armor by weight", e);
            System.err.println("Critical error occurred. Please check email for details.");
        }
    }
}

class FindArmorByPriceCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindArmorByPriceCommand.class);
    private AactionsOnArmor actions;
    private Scanner scanner;

    public FindArmorByPriceCommand(AactionsOnArmor actions, Scanner scanner) {
        this.actions = actions;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        logger.info("Executing FindArmorByPriceCommand");
        try {
            actions.findArmorByPrice(scanner);
            logger.info("Armor found by price range successfully");
        } catch (Exception e) {
            logger.fatal("Critical error while sorting armor by weight", e);
            System.err.println("Critical error occurred. Please check email for details.");
        }
    }
}

class RemoveArmorByNameAndPriceCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RemoveArmorByNameAndPriceCommand.class);
    private AactionsOnArmor actions;
    private Scanner scanner;

    public RemoveArmorByNameAndPriceCommand(AactionsOnArmor actions, Scanner scanner) {
        this.actions = actions;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        logger.info("Executing RemoveArmorByNameAndPriceCommand");
        try {
            actions.removeArmorByNameAndPrice(scanner);
            logger.info("Armor removed successfully");
        } catch (Exception e) {
            logger.fatal("Critical error while sorting armor by weight", e);
            System.err.println("Critical error occurred. Please check email for details.");
        }
    }
}

class LoadArmoryFromFileCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoadArmoryFromFileCommand.class);
    private AactionsOnArmor actions;

    public LoadArmoryFromFileCommand(AactionsOnArmor actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        logger.info("Executing LoadArmoryFromFileCommand");
        try {
            actions.loadArmoryFromFile("armory.txt");
            System.out.println("\nArmory loaded from file.\n");
            logger.info("Armory loaded from file successfully");
        }catch (Exception e) {
            logger.fatal("Critical error while sorting armor by weight", e);
            System.err.println("Critical error occurred. Please check email for details.");
        }
    }
}

class HelpCommand implements Command {
    private static final Logger logger = LogManager.getLogger(HelpCommand.class);

    @Override
    public void execute() {
        logger.info("Executing HelpCommand");
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
        logger.info("Help information displayed");
    }
}

class ExitCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ExitCommand.class);

    @Override
    public void execute() {
        logger.info("Executing ExitCommand");
        System.out.println("Exiting...");
        System.exit(0);
    }
}
