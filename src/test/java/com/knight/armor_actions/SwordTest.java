package com.knight.armor_actions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SwordTest {
    private Sword sword;

    @BeforeEach
    void setUp() {
        sword = new Sword("Knight Sword", 3.5, "Iron", 400.00);
    }

    @Test
    void testGetName() {
        assertEquals("Knight Sword", sword.getName());
    }

    @Test
    void testGetWeight() {
        assertEquals(3.5, sword.getWeight());
    }

    @Test
    void testGetMaterial() {
        assertEquals("Iron", sword.getMaterial());
    }

    @Test
    void testGetPrice() {
        assertEquals(400.00, sword.getPrice());
    }

    @Test
    void testToString() {
        String expected = "Knight Sword (Weight: 3.5 kg, Material: Iron, Price: $400.0)";
        assertEquals(expected, sword.toString());
    }

    @Test
    void testNegativeWeightThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Sword("Heavy Sword", -3.5, "Iron", 400);
        });
        assertEquals("Weight cannot be negative", exception.getMessage());
    }

    @Test
    void testNegativePriceThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Sword("Heavy Sword", 3.5, "Iron", -400);
        });
        assertEquals("Price cannot be negative", exception.getMessage());
    }

    @Test
    void testNullNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Sword(null, 3.5, "Iron", 400);
        });
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testNullMaterialThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Sword("Heavy Sword", 3.5, null, 400);
        });
        assertEquals("Material cannot be null or empty", exception.getMessage());
    }
}
