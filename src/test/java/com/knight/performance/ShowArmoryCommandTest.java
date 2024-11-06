package com.knight.performance;

import com.knight.armor_actions.AactionsOnArmor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

class ShowArmoryCommandTest {

    @Test
    void executeShouldCallShowArmory() {
        // Arrange
        AactionsOnArmor mockActions = Mockito.mock(AactionsOnArmor.class);
        ShowArmoryCommand command = new ShowArmoryCommand(mockActions);

        // Act
        command.execute();

        // Assert
        // Перевіряємо, що метод showArmory був викликаний
        verify(mockActions).showArmory();
    }
}
