package com.knight.performance;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class HelpCommandTest {

    @Test
    void execute() {
        // Захоплюємо вивід у консоль
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Виконуємо команду
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.execute();

        // Отримуємо вивід як рядок
        String output = outputStream.toString();

        // Перевіряємо, що вивід містить ключові фрази
        assertTrue(output.contains("--- Help ---"));
        assertTrue(output.contains("1. Add Armor - Add a new piece of armor to the knight's armory."));
        assertTrue(output.contains("2. Show Armory - Display all armor in the knight's armory."));
        assertTrue(output.contains("3. Calculate Total Price - Calculate the total price of all armor."));
        assertTrue(output.contains("4. Sort Armory by Weight - Sort all armor by increasing weight."));
        assertTrue(output.contains("5. Find Armory by Price Range - Search for armor within a specified price range."));
        assertTrue(output.contains("6. Save Armory to File - Save the current armory to a file."));
        assertTrue(output.contains("7. Load Armory from File - Load an armory from a file."));
        assertTrue(output.contains("8. Remove Armor by Name and Price - Remove an armor item by specifying its name and price."));
        assertTrue(output.contains("9. Exit - Exit the application."));

        // Повертаємо стандартний System.out
        System.setOut(System.out);
    }
}
