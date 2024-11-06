package com.knight.performance;

import com.knight.armor_actions.AactionsOnArmor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MainTest {

    private AactionsOnArmor knight;
    private Menu menu;
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        knight = mock(AactionsOnArmor.class); // Мок класу AactionsOnArmor
        InputStream in = new ByteArrayInputStream("1\n".getBytes());
        scanner = new Scanner(in);
        menu = new Menu(scanner);
    }

    @Test
    public void testAddArmorCommand() {
        // Додаємо команду
        Command addArmorCommand = new AddArmorCommand(knight, scanner);
        menu.addCommand(1, addArmorCommand);

        // Перевіряємо, що команда додалася
        assertNotNull(menu.getCommands()[1]);

        // Виконуємо команду
        addArmorCommand.execute();

        // Перевіряємо, що метод у knight викликаний
        verify(knight, timeout(500)).addArmor(any()); // Передбачте метод addArmor в AactionsOnArmor
    }

    @Test
    public void testShowArmoryCommand() {
        Command showArmoryCommand = new ShowArmoryCommand(knight);
        menu.addCommand(2, showArmoryCommand);

        // Виконуємо команду
        showArmoryCommand.execute();

        // Перевіряємо, що метод у knight викликаний
        verify(knight, timeout(500)).showArmory(); // Передбачте метод showArmory в AactionsOnArmor
    }

    @Test
    public void testCalculateTotalPriceCommand() {
        Command calculateTotalPriceCommand = new CalculateTotalPriceCommand(knight);
        menu.addCommand(3, calculateTotalPriceCommand);

        // Виконуємо команду
        calculateTotalPriceCommand.execute();

        // Перевіряємо, що метод у knight викликаний
        verify(knight, timeout(500)).calculateTotalPrice(); // Передбачте метод calculateTotalPrice в AactionsOnArmor
    }

    @Test
    public void testSortArmorByWeightCommand() {
        Command sortArmorByWeightCommand = new SortArmorByWeightCommand(knight);
        menu.addCommand(4, sortArmorByWeightCommand);

        // Виконуємо команду
        sortArmorByWeightCommand.execute();

        // Перевіряємо, що метод у knight викликаний
        verify(knight, timeout(500)).sortArmorByWeight(); // Передбачте метод sortArmorByWeight в AactionsOnArmor
    }
}