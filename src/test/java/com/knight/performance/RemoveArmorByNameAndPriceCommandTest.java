package com.knight.performance;
import com.knight.armor_actions.AactionsOnArmor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.mockito.Mockito.verify;

class RemoveArmorByNameAndPriceCommandTest {

    @Test
    void executeShouldRemoveArmorByNameAndPrice() {
        // Arrange
        AactionsOnArmor mockActions = Mockito.mock(AactionsOnArmor.class);
        Scanner mockScanner = Mockito.mock(Scanner.class);

        // Створюємо команду з макетами
        RemoveArmorByNameAndPriceCommand command = new RemoveArmorByNameAndPriceCommand(mockActions, mockScanner);

        // Act
        command.execute();

        // Assert
        // Перевіряємо, що метод removeArmorByNameAndPrice був викликаний з правильними аргументами
        verify(mockActions).removeArmorByNameAndPrice(mockScanner);
    }
}
