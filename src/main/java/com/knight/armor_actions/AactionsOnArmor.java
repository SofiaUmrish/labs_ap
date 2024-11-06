package com.knight.armor_actions;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AactionsOnArmor {
    private static final Logger logger = Logger.getLogger(AactionsOnArmor.class.getName());

    private Armor[] armory;
    private int armorCount;
    private String[] addedArmorTypes; // типи амуніції
    private int armorTypeCount;

    public AactionsOnArmor() {
        this.armory = new Armor[7];
        this.armorCount = 0;
        this.addedArmorTypes = new String[6];
        this.armorTypeCount = 0;
        logger.info("Object initialization AactionsOnArmor");
    }

    // Додавання амуніції
    public void addArmor(Scanner scanner) {
        scanner.nextLine();

        System.out.println("Enter armor type (Helmet, Shield, ArmorSuit, Sword, Lance, Bow): ");
        String armorType = scanner.nextLine().toLowerCase();

        if (isArmorTypeAdded(armorType)) {
            System.out.println("This armor type has already been added. Please choose another type.");
            logger.warning("Armor type " + armorType + " has already been added.");
            return;
        }

        double weight = 0;
        double price = 0;

        try {
            System.out.print("Enter weight: ");
            weight = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter material: ");
            String armorMaterial = scanner.nextLine();

            System.out.print("Enter price: ");
            price = scanner.nextDouble();
            scanner.nextLine();

            switch (armorType) {
                case "helmet":
                    equipArmor(new Helmet("Helmet", weight, armorMaterial, price));
                    break;
                case "shield":
                    equipArmor(new Shield("Shield", weight, armorMaterial, price));
                    break;
                case "armorsuit":
                    equipArmor(new ArmorSuit("Armorsuit", weight, armorMaterial, price));
                    break;
                case "sword":
                    equipArmor(new Sword("Sword", weight, armorMaterial, price));
                    break;
                case "lance":
                    equipArmor(new Lance("Lance", weight, armorMaterial, price));
                    break;
                case "bow":
                    equipArmor(new Bow("Bow", weight, armorMaterial, price));
                    break;
                default:
                    System.out.println("Invalid armor type. Please enter (Helmet, Shield, ArmorSuit, Sword, Lance, Bow).");
                    logger.warning("Invalid armor type: " + armorType);
                    return;
            }

            addedArmorTypes[armorTypeCount++] = armorType;
            logger.info("Armor " + armorType + " added with weight " + weight + " and price " + price);
        } catch (InputMismatchException e) {
            logger.log(Level.SEVERE, "Critical error: Ammunition input error", e);
            System.out.println("Critical error: Invalid input. Please enter numeric values for weight and price.");
            scanner.nextLine(); // очищення вводу
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error occurred while adding armor: " + e.getMessage(), e);
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); // очищення вводу
        }
    }

    // перевірка, чи тип обладунка вже додано
    private boolean isArmorTypeAdded(String armorType) {
        for (int i = 0; i < armorTypeCount; i++) {
            if (addedArmorTypes[i].equals(armorType)) {
                return true;
            }
        }
        return false;
    }

    // додавання в масив
    public void equipArmor(Armor armor) {
        if (armorCount < armory.length) {
            armory[armorCount++] = armor;
            logger.info("Equipped armor: " + armor);
        } else {
            System.out.println("Armory is full, cannot add more armor.");
            logger.warning("Failed to equip armor: Armory is full.");
        }
    }

    // пошуку амуніції в заданому діапазоні цін
    public void findArmorByPrice(Scanner scanner) {
        double minPrice, maxPrice;

        System.out.print("\nEnter minimum price for search: ");
        minPrice = scanner.nextDouble();

        System.out.print("Enter maximum price for search: ");
        maxPrice = scanner.nextDouble();

        Armor[] foundArmor = findArmorByPriceRange(minPrice, maxPrice);

        System.out.println("\nArmors in price range $" + minPrice + "-$" + maxPrice + ":");
        for (Armor armor : foundArmor) {
            if (armor != null) {
                System.out.println(armor);
            }
        }
    }

    // пошук за ціною
    public Armor[] findArmorByPriceRange(double minPrice, double maxPrice) {
        Armor[] result = new Armor[armorCount];
        int count = 0;
        for (int i = 0; i < armorCount; i++) {
            if (armory[i].getPrice() >= minPrice && armory[i].getPrice() <= maxPrice) {
                result[count++] = armory[i];
            }
        }
        logger.info("Found " + count + " armors in the price range $" + minPrice + "-$" + maxPrice);
        return result;
    }

    // Підрахунок загальної вартості
    public double calculateTotalPrice() {
        double total = 0;
        for (int i = 0; i < armorCount; i++) {
            total += armory[i].getPrice();
        }
        logger.info("Total price calculated: $" + total);
        return total;
    }

    // Сортування за вагою
    public void sortArmorByWeight() {
        for (int i = 0; i < armorCount - 1; i++) {
            for (int j = 0; j < armorCount - i - 1; j++) {
                if (armory[j].getWeight() > armory[j + 1].getWeight()) {
                    Armor temp = armory[j];
                    armory[j] = armory[j + 1];
                    armory[j + 1] = temp;
                }
            }
        }
        logger.info("Armors sorted by weight.");
    }

    // вивід
    public void showArmory() {
        System.out.println("Current armory:");
        for (int i = 0; i < armorCount; i++) {
            System.out.println(armory[i]);
        }
        logger.info("Displayed current armory.");
    }

    // видалення амуніції за назвою та ціною
    public void removeArmorByNameAndPrice(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter the name of the armor to remove: ");
        String armorName = scanner.nextLine();

        System.out.print("Enter the price of the armor to remove: ");
        double armorPrice = scanner.nextDouble();
        scanner.nextLine();

        boolean found = false;

        for (int i = 0; i < armorCount; i++) {
            if (armory[i].getName().equalsIgnoreCase(armorName) && armory[i].getPrice() == armorPrice) {
                for (int j = i; j < armorCount - 1; j++) {
                    armory[j] = armory[j + 1];
                }
                armory[--armorCount] = null;
                found = true;
                System.out.println("\nArmor " + armorName + " with price " + armorPrice + " has been removed.");
                logger.info("Removed armor: " + armorName + " with price " + armorPrice);
                break;
            }
        }
        if (!found) {
            System.out.println("\nArmor with name " + armorName + " and price " + armorPrice + " not found.");
            logger.warning("Attempted to remove non-existing armor: " + armorName + " with price " + armorPrice);
        }
    }

    // збереження у файл
    public void saveArmoryToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < armorCount; i++) {
                Armor armor = armory[i];
                writer.println(armor.getClass().getSimpleName() + "," + armor.getWeight() + "," + armor.getMaterial() + "," + armor.getPrice());
            }
            logger.info("Ammunition successfully saved to file: " + filename);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving to file: " + e.getMessage(), e);
        }
    }

    public void loadArmoryFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String type = data[0];

                if (!isArmorTypeAdded(type.toLowerCase())) {
                    double weight = Double.parseDouble(data[1]);
                    String material = data[2];
                    double price = Double.parseDouble(data[3]);

                    switch (type.toLowerCase()) {
                        case "helmet":
                            equipArmor(new Helmet(type, weight, material, price));
                            break;
                        case "shield":
                            equipArmor(new Shield(type, weight, material, price));
                            break;
                        case "armorsuit":
                            equipArmor(new ArmorSuit(type, weight, material, price));
                            break;
                        case "sword":
                            equipArmor(new Sword(type, weight, material, price));
                            break;
                        case "lance":
                            equipArmor(new Lance(type, weight, material, price));
                            break;
                        case "bow":
                            equipArmor(new Bow(type, weight, material, price));
                            break;
                        default:
                            logger.warning("Invalid armor type found in file: " + type);
                    }
                }
            }
            logger.info("Ammunition successfully loaded from file: " + filename);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading from file: " + e.getMessage(), e);
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid number format in file: " + e.getMessage(), e);
        }
    }
}
