package com.knight.performance;

import com.knight.armor_actions.AactionsOnArmor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;

class SaveArmoryToFileCommandTest {

    @Test
    void executeShouldSaveArmoryToFile() throws IOException {
        // Arrange
        AactionsOnArmor mockActions = Mockito.mock(AactionsOnArmor.class);
        SaveArmoryToFileCommand command = new SaveArmoryToFileCommand(mockActions);

        // Act
        command.execute();

        // Assert
        // Перевіряємо, що метод saveArmoryToFile був викликаний
        verify(mockActions).saveArmoryToFile("armory.txt");
    }

    @Test
    void executeShouldHandleIOException() throws IOException {
        // Arrange
        AactionsOnArmor mockActions = Mockito.mock(AactionsOnArmor.class);
        SaveArmoryToFileCommand command = new SaveArmoryToFileCommand(mockActions);

        // Використовуємо doThrow для імітації IOException
        doThrow(new IOException("Test exception")).when(mockActions).saveArmoryToFile("armory.txt");

        // Act & Assert
        // Виводимо результат в консоль, перевіряємо, що IOException обробляється
        command.execute(); // Тут тест не викине виключення, але можна додати перевірку виводу, якщо потрібно
    }
}
