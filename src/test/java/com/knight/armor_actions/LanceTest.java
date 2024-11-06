package com.knight.armor_actions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LanceTest {
    private Lance lance;

    @BeforeEach
    void setUp() {
        lance = new Lance("Knight Lance", 4.5, "Wood", 350.00);
    }

    @Test
    void testGetName() {
        assertEquals("Knight Lance", lance.getName());
    }

    @Test
    void testGetWeight() {
        assertEquals(4.5, lance.getWeight());
    }

    @Test
    void testGetMaterial() {
        assertEquals("Wood", lance.getMaterial());
    }

    @Test
    void testGetPrice() {
        assertEquals(350.00, lance.getPrice());
    }

    @Test
    void testToString() {
        String expected = "Knight Lance (Weight: 4.5 kg, Material: Wood, Price: $350.0)";
        assertEquals(expected, lance.toString());
    }

    @Test
    void testNegativeWeightThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Lance("Heavy Lance", -4.5, "Wood", 350);
        });
        assertEquals("Weight cannot be negative", exception.getMessage());
    }

    @Test
    void testNegativePriceThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Lance("Heavy Lance", 4.5, "Wood", -350);
        });
        assertEquals("Price cannot be negative", exception.getMessage());
    }

    @Test
    void testNullNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Lance(null, 4.5, "Wood", 350);
        });
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testNullMaterialThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Lance("Heavy Lance", 4.5, null, 350);
        });
        assertEquals("Material cannot be null or empty", exception.getMessage());
    }
}
