package com.knight.performance;

import com.knight.armor_actions.AactionsOnArmor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AddArmorCommandTest {

    private AactionsOnArmor actions;
    private AddArmorCommand addArmorCommand;

    @BeforeEach
    void setUp() {
        // Створюємо мок для AactionsOnArmor
        actions = Mockito.mock(AactionsOnArmor.class);
    }

    @Test
    void execute_addMultipleArmors() {

        String input = "2\nWood\n300\nWood\n300\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        // Створюємо об'єкт AddArmorCommand
        addArmorCommand = new AddArmorCommand(actions, scanner);

        // Викликаємо метод execute
        addArmorCommand.execute();

        // Перевіряємо, що addArmor було викликано двічі
        verify(actions, times(2)).addArmor(scanner);
    }

    @Test
    void execute_addSingleArmor() {
        // Імітуємо введення користувача: 1 амуніція, потім "Steel" та 500
        String input = "1\nSteel\n500\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        // Створюємо об'єкт AddArmorCommand
        addArmorCommand = new AddArmorCommand(actions, scanner);

        // Викликаємо метод execute
        addArmorCommand.execute();

        // Перевіряємо, що addArmor було викликано один раз
        verify(actions, times(1)).addArmor(scanner);
    }
}
