package com.knight.armor_actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AactionsOnArmorTest {

    private AactionsOnArmor armorActions;
    private Armor[] armory;
    @BeforeEach
    void setUp() {
        armory = new Armor[7];
        armorActions = new AactionsOnArmor();
    }

    @Test
    void testAddArmor() {
        // Коректне додавання броні
        String input = "Helmet\n2,5\nSteel\n500\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        armorActions.addArmor(scanner);
        assertEquals(1, armorActions.getArmorCount(), "Armor must be added");

        // Додавання дубліката типу амуніції
        String duplicateInput = "Helmet\n3\nIron\n600\n";
        scanner = new Scanner(new ByteArrayInputStream(duplicateInput.getBytes()));
        armorActions.addArmor(scanner);
        assertEquals(1, armorActions.getArmorCount(), "Duplicate armor should not be added");

        // Некоректне введення типу
        String invalidTypeInput = "NonExistentArmor\n2,5\nSteel\n500\n";
        scanner = new Scanner(new ByteArrayInputStream(invalidTypeInput.getBytes()));
        armorActions.addArmor(scanner);
        assertEquals(1, armorActions.getArmorCount(), "Unknown armor type should not be added");

        // Некоректне введення ваги
        String invalidWeightInput = "Shield\nnot_a_number\nIron\n600\n";
        scanner = new Scanner(new ByteArrayInputStream(invalidWeightInput.getBytes()));
        armorActions.addArmor(scanner);
        assertEquals(1, armorActions.getArmorCount(), "Armor should not be added due to incorrect weight input");

        // Некоректне введення ціни
        String invalidPriceInput = "Shield\n2,5\nIron\nnot_a_number\n";
        scanner = new Scanner(new ByteArrayInputStream(invalidPriceInput.getBytes()));
        armorActions.addArmor(scanner);
        assertEquals(1, armorActions.getArmorCount(), "Armor should not be added due to incorrect price entry");
    }

    @Test
    void testEquipArmor() {
        // Додаємо амуніцію
        armorActions.equipArmor(new Helmet("Helmet", 2.5, "steel", 500.0));
        assertEquals(1, armorActions.getArmorCount(), "Armory should contain one armor after addition");

        // Додаємо ще одну амуніцію
        armorActions.equipArmor(new Shield("Shield", 4.0, "iron", 300.0));
        assertEquals(2, armorActions.getArmorCount(), "Armory should contain two armors after addition");

        // Тестуємо заповненість масиву
        for (int i = 0; i < armory.length - 2; i++) {
            armorActions.equipArmor(new ArmorSuit("Armorsuit " + i, 10.0, "leather", 200.0));
        }

        // Перевірка, що масив заповнений, і викидається виняток
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            armorActions.equipArmor(new ArmorSuit("Excess Armor", 10.0, "leather", 200.0));
        });

        assertEquals("Armory is full, cannot add more armor.", exception.getMessage());
    }

    @Test
    void testFindArmorByPriceRange() {
        armorActions.equipArmor(new Helmet("Helmet", 2.5, "steel", 500.0));
        armorActions.equipArmor(new Shield("Shield", 4.0, "iron", 300.0));

        // Стандартний випадок
        Armor[] result = armorActions.findArmorByPriceRange(200, 400);
        assertEquals(1, result.length);
        assertTrue(result[0] instanceof Shield);

        // Діапазон без результатів
        result = armorActions.findArmorByPriceRange(600, 700);
        assertEquals(0, result.length);
    }

    @Test
    void testCalculateTotalPrice() {
        armorActions.equipArmor(new Helmet("Helmet", 2.5, "steel", 500.0));
        armorActions.equipArmor(new Shield("Shield", 4.0, "iron", 300.0));

        // Стандартний випадок
        double totalPrice = armorActions.calculateTotalPrice();
        assertEquals(800.0, totalPrice);

        // Порожній масив
        AactionsOnArmor emptyActions = new AactionsOnArmor();
        assertEquals(0.0, emptyActions.calculateTotalPrice());

    }

    @Test
    void testSortArmorByWeight() {
        armorActions.equipArmor(new Helmet("Helmet", 2.5, "steel", 500.0));
        armorActions.equipArmor(new Shield("Shield", 4.0, "iron", 300.0));
        armorActions.equipArmor(new Lance("Lance", 1.5, "wood", 200.0));

        armorActions.sortArmorByWeight();

        Armor[] result = armorActions.findArmorByPriceRange(0, 1000);
        assertTrue(result[0] instanceof Lance);
        assertTrue(result[1] instanceof Helmet);
        assertTrue(result[2] instanceof Shield);
    }

    @Test
    void testSortEmptyArmor() {
        // Сортування порожнього арсеналу
        AactionsOnArmor emptyActions = new AactionsOnArmor();
        emptyActions.sortArmorByWeight();
        assertEquals(0, emptyActions.findArmorByPriceRange(0, 1000).length);
    }

    @Test
    void testRemoveArmorByNameAndPrice() {
        // Додаємо амуніцію
        armorActions.equipArmor(new Helmet("Helmet", 5, "Steel", 100));
        armorActions.equipArmor(new Shield("Shield", 7, "Wood", 150));
        armorActions.equipArmor(new ArmorSuit("Armorsuit", 10, "Leather", 200));

        // Вхідні дані для успішного видалення
        String inputSuccess = "\nHelmet\n100\n";
        try (Scanner scanner = new Scanner(new ByteArrayInputStream(inputSuccess.getBytes()))) {
            armorActions.removeArmorByNameAndPrice(scanner);
        }

        // Перевірка після видалення
        assertEquals(2, armorActions.getArmorCount());
        assertNull(armorActions.findArmorByName("Helmet"));

        // Вхідні дані для спроби видалення неіснуючої амуніції
        String inputNotFound = "\nNonExistentArmor\n200\n";
        try (Scanner scanner = new Scanner(new ByteArrayInputStream(inputNotFound.getBytes()))) {
            armorActions.removeArmorByNameAndPrice(scanner);
        }

        // Перевірка, що кількість амуніції не змінилася
        assertEquals(2, armorActions.getArmorCount());
    }


    @Test
    void testSaveAndLoadArmory() throws IOException {
        armorActions.equipArmor(new Helmet("Helmet", 2.5, "steel", 500.0));
        armorActions.equipArmor(new Shield("Shield", 4.0, "iron", 300.0));

        // Зберігаємо в тимчасовий файл
        File tempFile = File.createTempFile("testArmory", ".txt");
        tempFile.deleteOnExit();
        armorActions.saveArmoryToFile(tempFile.getAbsolutePath());

        // Створюємо новий екземпляр і завантажуємо з файлу
        AactionsOnArmor loadedArmorActions = new AactionsOnArmor();
        loadedArmorActions.loadArmoryFromFile(tempFile.getAbsolutePath());

        assertEquals(2, loadedArmorActions.findArmorByPriceRange(0, 1000).length);
        assertEquals(armorActions.calculateTotalPrice(), loadedArmorActions.calculateTotalPrice());
    }

    @Test
    void testSaveToFileError() {
        armorActions.equipArmor(new Helmet("Helmet", 2.5, "steel", 500.0));

        // Спровокувати IOException, зберігаючи у неіснуючий файл
        assertThrows(IOException.class, () -> {
            armorActions.saveArmoryToFile("/invalid/path/test.txt");
        });
    }

    @Test
    void testLoadFromFileError() {
        // Спровокувати IOException, завантажуючи з неіснуючого файлу
        assertThrows(IOException.class, () -> {
            armorActions.loadArmoryFromFile("nonexistent_file.txt");
        });
    }

    @Test
    void testLoadArmorDuplicateTypeFromFile() throws IOException {
        // Створюємо тимчасовий файл з дублікатами типів
        File tempFile = File.createTempFile("testDuplicateArmory", ".txt");
        tempFile.deleteOnExit();

        try (PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {
            writer.println("Helmet,2.5,steel,500.0");
            writer.println("Helmet,3.0,iron,600.0"); // Дубльований тип
        }

        // Завантаження з файлу має врахувати дублікати
        AactionsOnArmor loadedArmorActions = new AactionsOnArmor();
        loadedArmorActions.loadArmoryFromFile(tempFile.getAbsolutePath());

        assertEquals(1, loadedArmorActions.findArmorByPriceRange(0, 1000).length); // Тільки один тип має бути доданий
    }

    @Test
    void testFindArmorByName() {
        // Додаємо амуніцію
        armorActions.equipArmor(new Helmet("Helmet", 2.5, "Steel", 500.0));
        armorActions.equipArmor(new Shield("Shield", 4.0, "Iron", 300.0));
        armorActions.equipArmor(new ArmorSuit("Armorsuit", 10.0, "Leather", 200.0));

        // Перевірка наявності амуніції за назвою
        Armor foundArmor = armorActions.findArmorByName("Helmet");
        assertNotNull(foundArmor, "Armor should be found by name");
        assertEquals("Helmet", foundArmor.getName(), "The found armor should be the Helmet");

        // Перевірка на наявність амуніції за назвою, яка не існує
        Armor notFoundArmor = armorActions.findArmorByName("NonExistentArmor");
        assertNull(notFoundArmor, "Armor should not be found if it does not exist");
    }



    @Test
    void testSortArmorByWeightWithMultipleTypes() {
        armorActions.equipArmor(new Helmet("Light Helmet", 1.0, "steel", 300.0));
        armorActions.equipArmor(new Shield("Heavy Shield", 5.0, "iron", 400.0));
        armorActions.equipArmor(new ArmorSuit("Medium Armor", 3.0, "leather", 500.0));

        armorActions.sortArmorByWeight();

        Armor[] result = armorActions.findArmorByPriceRange(0, 1000);
        assertTrue(result[0] instanceof Helmet);
        assertTrue(result[1] instanceof ArmorSuit);
        assertTrue(result[2] instanceof Shield);
    }

    @Test
    void testGetArmorCount() {
        assertEquals(0, armorActions.getArmorCount());

        armorActions.equipArmor(new Helmet("Helmet", 2.5, "steel", 500.0));
        assertEquals(1, armorActions.getArmorCount());
    }
}
