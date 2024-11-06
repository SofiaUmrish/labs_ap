package com.knight.armor_actions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowTest {
    private Bow bow;

    @BeforeEach
    void setUp() {
        bow = new Bow("Knight Bow", 1.5, "Wood", 250.00);
    }

    @Test
    void testGetName() {
        assertEquals("Knight Bow", bow.getName());
    }

    @Test
    void testGetWeight() {
        assertEquals(1.5, bow.getWeight());
    }

    @Test
    void testGetMaterial() {
        assertEquals("Wood", bow.getMaterial());
    }

    @Test
    void testGetPrice() {
        assertEquals(250.00, bow.getPrice());
    }

    @Test
    void testToString() {
        String expected = "Knight Bow (Weight: 1.5 kg, Material: Wood, Price: $250.0)";
        assertEquals(expected, bow.toString());
    }

    @Test
    void testNegativeWeightThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Bow("Heavy Bow", -1.5, "Wood", 250);
        });
        assertEquals("Weight cannot be negative", exception.getMessage());
    }

    @Test
    void testNegativePriceThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Bow("Heavy Bow", 1.5, "Wood", -250);
        });
        assertEquals("Price cannot be negative", exception.getMessage());
    }

    @Test
    void testNullNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Bow(null, 1.5, "Wood", 250);
        });
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testNullMaterialThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Bow("Heavy Bow", 1.5, null, 250);
        });
        assertEquals("Material cannot be null or empty", exception.getMessage());
    }
}
