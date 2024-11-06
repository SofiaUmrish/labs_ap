package com.knight.performance;

import com.knight.armor_actions.AactionsOnArmor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.mockito.Mockito.verify;

class FindArmorByPriceCommandTest {

    @Test
    void execute() {
        // Створюємо mock для AactionsOnArmor
        AactionsOnArmor mockActions = Mockito.mock(AactionsOnArmor.class);

        // Створюємо mock для Scanner
        Scanner mockScanner = Mockito.mock(Scanner.class);

        // Створюємо об'єкт FindArmorByPriceCommand з mock об'єктами
        FindArmorByPriceCommand command = new FindArmorByPriceCommand(mockActions, mockScanner);

        // Викликаємо метод execute
        command.execute();

        // Перевіряємо, чи було викликано метод findArmorByPrice зі Scanner
        verify(mockActions).findArmorByPrice(mockScanner);
    }
}
