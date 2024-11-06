package com.knight.performance;

import com.knight.armor_actions.AactionsOnArmor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class CalculateTotalPriceCommandTest {

    private AactionsOnArmor actions; // Mock об'єкт
    private CalculateTotalPriceCommand command;

    @BeforeEach
    void setUp() {
        actions = mock(AactionsOnArmor.class); // Створюємо mock для AactionsOnArmor
        command = new CalculateTotalPriceCommand(actions); // Ініціалізуємо команду з mock об'єктом
    }

    @Test
    void testExecute() {
        // Задаємо повернене значення методу calculateTotalPrice
        when(actions.calculateTotalPrice()).thenReturn(500.0);

        // Викликаємо метод execute
        command.execute();

        // Перевіряємо, чи був викликаний метод calculateTotalPrice один раз
        verify(actions, times(1)).calculateTotalPrice();
    }
}
