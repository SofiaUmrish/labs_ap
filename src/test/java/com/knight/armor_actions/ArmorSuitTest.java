package com.knight.armor_actions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArmorSuitTest {
    private ArmorSuit armorSuit;

    @BeforeEach
    void setUp() {

        armorSuit = new ArmorSuit("Armor Suit", 15.5, "Steel", 1200.00);
    }

    @Test
    void testGetName() {
        assertEquals("Armor Suit", armorSuit.getName());
    }

    @Test
    void testGetWeight() {
        assertEquals(15.5, armorSuit.getWeight());
    }

    @Test
    void testGetMaterial() {
        assertEquals("Steel", armorSuit.getMaterial());
    }

    @Test
    void testGetPrice() {
        assertEquals(1200.00, armorSuit.getPrice());
    }

    @Test
    void testToString() {
        String expected = "Armor Suit (Weight: 15.5 kg, Material: Steel, Price: $1200.0)";
        assertEquals(expected, armorSuit.toString());
    }

    // Тест на виключення при від'ємній вазі
    @Test
    void testNegativeWeightThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ArmorSuit("Heavy Armor", -10.0, "Steel", 1500);
        });
        assertEquals("Weight cannot be negative", exception.getMessage());
    }

    // Тест на виключення при від'ємній ціні
    @Test
    void testNegativePriceThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ArmorSuit("Heavy Armor", 15.0, "Steel", -1500);
        });
        assertEquals("Price cannot be negative", exception.getMessage());
    }

    // Тест на виключення при null назві
    @Test
    void testNullNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ArmorSuit(null, 10.0, "Steel", 1000);
        });
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    // Тест на виключення при null матеріалі
    @Test
    void testNullMaterialThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ArmorSuit("Heavy Armor", 10.0, null, 1000);
        });
        assertEquals("Material cannot be null or empty", exception.getMessage());
    }
}
