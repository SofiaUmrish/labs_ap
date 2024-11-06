package com.knight.armor_actions;
import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
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

    public int getArmorCount() {
        return armorCount;
    }

    // Додавання амуніції
    public void addArmor(Scanner scanner) {
        System.out.println("Enter armor type (Helmet, Shield, ArmorSuit, Sword, Lance, Bow): ");
        String armorType = scanner.nextLine().toLowerCase();

        // Перевіряємо, чи вже було додано цей тип броні
        if (isArmorTypeAdded(armorType)) {
            System.out.println("This armor type has already been added. Please choose another type.");
            return;
        }

        double weight = 0;
        double price = 0;

        // Зчитуємо вагу
        System.out.print("Enter weight: ");
        try {
            weight = scanner.nextDouble();
            if (weight <= 0) {
                System.out.println("Weight must be positive.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Weight must be a number.");
            scanner.nextLine();  // Очищаємо буфер після помилки вводу
            return;
        }

        // Очищаємо буфер після введення ваги
        scanner.nextLine();

        // Зчитуємо матеріал
        System.out.print("Enter material: ");
        String armorMaterial = scanner.nextLine();
        if (armorMaterial.isEmpty()) {
            System.out.println("Material cannot be empty.");
            return;
        }

        // Зчитуємо ціну
        System.out.print("Enter price: ");
        try {
            price = scanner.nextDouble();
            if (price <= 0) {
                System.out.println("Price must be positive.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Price must be a number.");
            scanner.nextLine();  // Очищаємо буфер після помилки вводу
            return;
        }

        // Очищаємо буфер після введення ціни
        scanner.nextLine();

        // Додаємо броню відповідно до типу
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

        // Додаємо тип броні до списку, якщо він був доданий успішно
        addedArmorTypes[armorTypeCount++] = armorType;
    }


    // перевірка, чи тип обладунка вже додано
    public boolean isArmorTypeAdded(String armorType) {
        for (int i = 0; i < armorTypeCount; i++) {
            if (addedArmorTypes[i].equalsIgnoreCase(armorType)) {
                return true; // Якщо броня вже додана, повертаємо true
            }
        }
        return false; // Якщо броня ще не додана
    }


    // додавання в масив
    public void equipArmor(Armor armor) {
        if (armorCount < armory.length) {
            armory[armorCount++] = armor;
        } else {
            throw new ArrayIndexOutOfBoundsException("Armory is full, cannot add more armor.");
        }

    }

    //пошуку амуніції в заданому діапазоні цін
    public void findArmorByPrice(Scanner scanner) {
        double minPrice, maxPrice;

        System.out.print("\nEnter minimum price for search: ");
        minPrice = scanner.nextDouble();

        System.out.print("Enter maximum price for search: ");
        maxPrice = scanner.nextDouble();

        if (minPrice > maxPrice) {
            System.out.println("Minimum price cannot be greater than maximum price.");
            return;
        }

        Armor[] foundArmor = findArmorByPriceRange(minPrice, maxPrice);

        System.out.println("\n Armors in price range $" + minPrice + "-$" + maxPrice + ":");
        for (int i = 0; i < foundArmor.length; i++) {
            if (foundArmor[i] != null) {
                System.out.println(foundArmor[i]);
            }
        }
    }
    //пошука за імям
    public Armor findArmorByName(String name) {
        for (int i = 0; i < armorCount; i++) {
            if (armory[i].getName().equalsIgnoreCase(name)) {
                return armory[i]; // Якщо знайдено амуніцію з такою назвою, повертаємо її
            }
        }
        return null; // Якщо не знайдено, повертаємо null
    }



    // пошук за ціною
    public Armor[] findArmorByPriceRange(double minPrice, double maxPrice) {
        Armor[] tempResult = new Armor[armorCount];
        int count = 0;

        // Проходимо по всьому списку амуніції
        for (int i = 0; i < armorCount; i++) {
            if (armory[i].getPrice() >= minPrice && armory[i].getPrice() <= maxPrice) {
                tempResult[count++] = armory[i];
            }
        }

        // Створюємо масив точного розміру для повернення результату
        Armor[] result = new Armor[count];
        System.arraycopy(tempResult, 0, result, 0, count);

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
    public void saveArmoryToFile(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < armorCount; i++) {
                Armor armor = armory[i];
                writer.println(armor.getClass().getSimpleName() + "," + armor.getWeight() + "," + armor.getMaterial() + "," + armor.getPrice());
            }
        }
    }
    // завантаження з файлу
    public void loadArmoryFromFile(String filename) throws IOException { // Додаємо throws IOException
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String type = data[0];

                // Перевірка, чи тип вже додано
                if (!isArmorTypeAdded(type.toLowerCase())) {
                    try {
                        double weight = Double.parseDouble(data[1]);
                        String material = data[2];
                        double price = Double.parseDouble(data[3]);

                        // Перевірка на коректність даних
                        if (weight <= 0 || price <= 0 || material.isEmpty()) {
                            throw new IllegalArgumentException("Invalid data in file.");
                        }

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
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing numeric values from file: " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error in data from file: " + e.getMessage());
                    }
                } else {
                    System.out.println("Armor type " + type + " has already been added.");
                }
            }
        } catch (IOException e) {
            // Кидаємо IOException далі, щоб тест міг його зловити
            throw e;
        }
    }

}
