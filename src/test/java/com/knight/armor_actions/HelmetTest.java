package com.knight.armor_actions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HelmetTest {
    private Helmet helmet;

    @BeforeEach
    void setUp() {
        helmet = new Helmet("Knight Helmet", 2.0, "Steel", 200.00);
    }

    @Test
    void testGetName() {
        assertEquals("Knight Helmet", helmet.getName());
    }

    @Test
    void testGetWeight() {
        assertEquals(2.0, helmet.getWeight());
    }

    @Test
    void testGetMaterial() {
        assertEquals("Steel", helmet.getMaterial());
    }

    @Test
    void testGetPrice() {
        assertEquals(200.00, helmet.getPrice());
    }

    @Test
    void testToString() {
        String expected = "Knight Helmet (Weight: 2.0 kg, Material: Steel, Price: $200.0)";
        assertEquals(expected, helmet.toString());
    }

    @Test
    void testNegativeWeightThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Helmet("Heavy Helmet", -2.0, "Steel", 200);
        });
        assertEquals("Weight cannot be negative", exception.getMessage());
    }

    @Test
    void testNegativePriceThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Helmet("Heavy Helmet", 2.0, "Steel", -200);
        });
        assertEquals("Price cannot be negative", exception.getMessage());
    }

    @Test
    void testNullNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Helmet(null, 2.0, "Steel", 200);
        });
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testNullMaterialThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Helmet("Heavy Helmet", 2.0, null, 200);
        });
        assertEquals("Material cannot be null or empty", exception.getMessage());
    }
}
