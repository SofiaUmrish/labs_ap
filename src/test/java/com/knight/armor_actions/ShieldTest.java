package com.knight.armor_actions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShieldTest {
    private Shield shield;

    @BeforeEach
    void setUp() {
        shield = new Shield("Knight Shield", 5.0, "Wood", 300.00);
    }

    @Test
    void testGetName() {
        assertEquals("Knight Shield", shield.getName());
    }

    @Test
    void testGetWeight() {
        assertEquals(5.0, shield.getWeight());
    }

    @Test
    void testGetMaterial() {
        assertEquals("Wood", shield.getMaterial());
    }

    @Test
    void testGetPrice() {
        assertEquals(300.00, shield.getPrice());
    }

    @Test
    void testToString() {
        String expected = "Knight Shield (Weight: 5.0 kg, Material: Wood, Price: $300.0)";
        assertEquals(expected, shield.toString());
    }

    @Test
    void testNegativeWeightThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Shield("Heavy Shield", -5.0, "Wood", 300);
        });
        assertEquals("Weight cannot be negative", exception.getMessage());
    }

    @Test
    void testNegativePriceThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Shield("Heavy Shield", 5.0, "Wood", -300);
        });
        assertEquals("Price cannot be negative", exception.getMessage());
    }

    @Test
    void testNullNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Shield(null, 5.0, "Wood", 300);
        });
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testNullMaterialThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Shield("Heavy Shield", 5.0, null, 300);
        });
        assertEquals("Material cannot be null or empty", exception.getMessage());
    }
}
