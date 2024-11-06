package com.knight.performance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuTest {

    private Menu menu;
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        //Симуляція користувацького вводу
        String input = "2\n10\n"; // Приклад введення: спочатку команда 2, потім 10
        InputStream in = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(in);
        menu = new Menu(scanner);
    }

    @Test
    void getCommands() {
        // Перевіряємо, що масив команд не дорівнює null і містить 11 елементів
        assertNotNull(menu.getCommands());
        assertEquals(12, menu.getCommands().length);
    }

    @Test
    void addCommand() {
        Command mockCommand = Mockito.mock(Command.class);
        menu.addCommand(1, mockCommand);

        // Перевіряємо, що команда була додана до масиву команд у потрібній позиції
        assertEquals(mockCommand, menu.getCommands()[1]);
    }

    @Test
    void runWithValidInput() {
        // Створюємо дві команди для перевірки
        Command mockShowCommand = mock(Command.class);
        Command mockExitCommand = mock(Command.class);

        // Додаємо команди до меню
        menu.addCommand(2, mockShowCommand);
        menu.addCommand(10, mockExitCommand);

        // Викликаємо метод run() у окремому потоці
        Thread menuThread = new Thread(menu::run);
        menuThread.start();

        try {
            // Даємо трохи часу для виконання run() та імітації натискання команд
            Thread.sleep(100);

            // Перевіряємо, чи виконались команди Show Armory та Exit
            verify(mockShowCommand, times(1)).execute();
            verify(mockExitCommand, times(1)).execute();

        } catch (InterruptedException e) {
            fail("Test interrupted");
        } finally {
            // Закриваємо сканер після виконання тесту
            scanner.close();
        }
    }

    @Test
    void runWithInvalidInput() {
        // Створюємо підміну введення для симуляції некоректного користувацького вводу
        String input = "12\n3\n2\n"; // Некоректна команда, потім команда 2 (Show Armory)
        InputStream in = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(in);
        menu = new Menu(scanner);

        // Створюємо команду для перевірки
        Command mockShowCommand = mock(Command.class);
        menu.addCommand(2, mockShowCommand); // Show Armory команда

        // Викликаємо метод run() у окремому потоці
        Thread menuThread = new Thread(menu::run);
        menuThread.start();

        try {
            // Даємо трохи часу для виконання run() та імітації натискання команд
            Thread.sleep(100);

            // Перевіряємо, чи була виконана команда Show Armory
            verify(mockShowCommand, times(1)).execute();
        } catch (InterruptedException e) {
            fail("Test interrupted");
        } finally {
            // Закриваємо сканер після виконання тесту
            scanner.close();
        }
    }

    @Test
    void addCommandInvalidOption() {
        Command mockCommand = Mockito.mock(Command.class);

        // Спробуємо додати команду з недійсним індексом
        menu.addCommand(0, mockCommand); // 0 є недійсним індексом
        menu.addCommand(11, mockCommand); // 11 є недійсним індексом

        // Перевіряємо, що команда не була додана
        assertNull(menu.getCommands()[0]);
        assertNull(menu.getCommands()[10]); // 11-й індекс у масиві з 0-індексацією
    }

    @Test
    void runWithInvalidCommandOption() {
        // Додаємо команду до меню
        Command mockShowCommand = mock(Command.class);
        menu.addCommand(2, mockShowCommand);

        // Викликаємо метод run() у окремому потоці
        Thread menuThread = new Thread(menu::run);
        menuThread.start();

        try {
            // Імітуємо введення некоректного індексу
            String input = "0\n12\n2\n"; // Некоректні команди, потім команда 2 (Show Armory)
            InputStream in = new ByteArrayInputStream(input.getBytes());
            scanner = new Scanner(in);
            menu = new Menu(scanner); // Перезапускаємо меню з новим сканером

            Thread.sleep(100); // Даємо трохи часу для виконання run()

            // Перевіряємо, що команда Show Armory була виконана
            verify(mockShowCommand, times(1)).execute();
        } catch (InterruptedException e) {
            fail("Test interrupted");
        } finally {
            scanner.close(); // Закриваємо сканер після виконання тесту
        }
    }


}
