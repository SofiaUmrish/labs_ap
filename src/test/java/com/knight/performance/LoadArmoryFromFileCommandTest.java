package com.knight.performance;

import com.knight.armor_actions.AactionsOnArmor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class LoadArmoryFromFileCommandTest {

    @Test
    void executeShouldLoadArmoryFromFileSuccessfully() throws IOException {
        // Arrange
        AactionsOnArmor mockActions = Mockito.mock(AactionsOnArmor.class);
        LoadArmoryFromFileCommand command = new LoadArmoryFromFileCommand(mockActions);

        // Act
        command.execute();

        // Assert
        verify(mockActions).loadArmoryFromFile("armory.txt");  // Перевіряємо, що метод був викликаний
    }

    @Test
    void executeShouldHandleIOException() throws IOException {
        // Arrange
        AactionsOnArmor mockActions = Mockito.mock(AactionsOnArmor.class);
        LoadArmoryFromFileCommand command = new LoadArmoryFromFileCommand(mockActions);
        doThrow(new IOException("File not found")).when(mockActions).loadArmoryFromFile("armory.txt");

        // Act
        command.execute();

        // No exception should be thrown, just error message printed
        // Assert - тут можна перевірити, чи було оброблено виведення у консоль, використовуючи OutputCapture або інші бібліотеки для тестування
    }
}
