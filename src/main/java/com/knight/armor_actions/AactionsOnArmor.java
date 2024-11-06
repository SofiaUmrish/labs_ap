package com.knight.armor_actions;
import java.io.*;
import java.util.Scanner;

public class AactionsOnArmor {
    private Armor[] armory;
    private int armorCount;
    private String[] addedArmorTypes; // типи амуніції
    private int armorTypeCount;

    public AactionsOnArmor() {
        this.armory = new Armor[7];
        this.armorCount = 0;
        this.addedArmorTypes = new String[6];
        this.armorTypeCount = 0;
    }

    // Додавання амуніції
    public void addArmor(Scanner scanner) {
        scanner.nextLine();

        System.out.println("Enter armor type (Helmet, Shield, ArmorSuit, Sword, Lance, Bow): ");
        String armorType = scanner.nextLine().toLowerCase();

        if (isArmorTypeAdded(armorType)) {
            System.out.println("This armor type has already been added. Please choose another type.");
            return;
        }

        double weight = 0;
        double price = 0;

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
                return; // Вихід з методу, якщо тип невірний
        }

        addedArmorTypes[armorTypeCount++] = armorType; // Збільшуємо armorTypeCount після додавання
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
        } else {
            System.out.println("Armory is full, cannot add more armor.");
        }
    }

    //пошуку амуніції в заданому діапазоні цін
    public void findArmorByPrice(Scanner scanner) {
        double minPrice, maxPrice;

        System.out.print("\nEnter minimum price for search: ");
        minPrice = scanner.nextDouble();

        System.out.print("Enter maximum price for search: ");
        maxPrice = scanner.nextDouble();

        Armor[] foundArmor = findArmorByPriceRange(minPrice, maxPrice);

        System.out.println("\n Armors in price range $" + minPrice + "-$" + maxPrice + ":");
        for (int i = 0; i < foundArmor.length; i++) {
            if (foundArmor[i] != null) {
                System.out.println(foundArmor[i]);
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
        return result;
    }

    // Підрахунок загальної вартості
    public double calculateTotalPrice() {
        double total = 0;
        for (int i = 0; i < armorCount; i++) {
            total += armory[i].getPrice();
        }
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
    }

    // вивід
    public void showArmory() {
        for (int i = 0; i < armorCount; i++) {
            System.out.println(armory[i]);
        }
    }
    //видалення амуніції за назвою та ціною
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
                break;
            }
        }
        if (!found) {
            System.out.println("\nArmor with name " + armorName + " and price " + armorPrice + " not found.");
        }
    }
    // збереження у файл
    public void saveArmoryToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < armorCount; i++) {
                Armor armor = armory[i];
                writer.println(armor.getClass().getSimpleName() + "," + armor.getWeight() + "," + armor.getMaterial() + "," + armor.getPrice());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // завантаження з файлу
    public void loadArmoryFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String type = data[0];

                // Перевірка, чи тип вже додано
                if (!isArmorTypeAdded(type.toLowerCase())) {
                    double weight = Double.parseDouble(data[1]);
                    String material = data[2];
                    double price = Double.parseDouble(data[3]);

                    switch (type.toLowerCase()) {
                        case "helmet":
                            equipArmor(new Helmet("Helmet", weight, material, price));
                            break;
                        case "shield":
                            equipArmor(new Shield("Shield", weight, material, price));
                            break;
                        case "armorsuit":
                            equipArmor(new ArmorSuit("Armorsuit", weight, material, price));
                            break;
                        case "sword":
                            equipArmor(new Sword("Sword", weight, material, price));
                            break;
                        case "lance":
                            equipArmor(new Lance("Lance", weight, material, price));
                            break;
                        case "bow":
                            equipArmor(new Bow("Bow", weight, material, price));
                            break;
                        default:
                            System.out.println("Unknown armor type: " + type);
                            break;
                    }

                    // Додаємо тип до addedArmorTypes
                    addedArmorTypes[armorTypeCount++] = type.toLowerCase(); // Збільшуємо armorTypeCount
                } else {
                    System.out.println("Armor type " + type + " has already been added.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}