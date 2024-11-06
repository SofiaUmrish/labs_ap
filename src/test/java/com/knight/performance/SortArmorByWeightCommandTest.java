package com.knight.performance;

import com.knight.armor_actions.AactionsOnArmor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

class SortArmorByWeightCommandTest {

    @Test
    void executeShouldSortAndShowArmory() {
        // Arrange
        AactionsOnArmor mockActions = Mockito.mock(AactionsOnArmor.class);
        SortArmorByWeightCommand command = new SortArmorByWeightCommand(mockActions);

        // Act
        command.execute();

        // Assert
        // Перевіряємо, що метод sortArmorByWeight був викликаний
        verify(mockActions).sortArmorByWeight();
        // Перевіряємо, що метод showArmory був викликаний
        verify(mockActions).showArmory();
    }
}
