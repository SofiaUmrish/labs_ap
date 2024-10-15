package com.droid_game.battle;

import com.droid_game.battle.arena.Arena;
import com.droid_game.droids.Droid;
import com.droid_game.record_battle.RecordBattle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BattleOneOnOne {
    private Droid droid1;
    private Droid droid2;
    private Arena arena;
    private RecordBattle logger; // Об'єкт для логування
    private StringBuilder battleLogBuffer; // Буфер для логів
    private boolean loggingEnabled; // Чи увімкнено логування

    // Конструктор для ініціалізації дроїдів та логера
    public BattleOneOnOne(Droid d1, Droid d2, Arena arena, String logFilePath, boolean loggingEnabled) throws IOException {
        this.droid1 = d1;
        this.droid2 = d2;
        this.arena = arena;

        this.battleLogBuffer = new StringBuilder();
        this.loggingEnabled = loggingEnabled;

        // Змінені характеристики дроїдів відповідно до місця битви
        System.out.println("\nЗмінені характеристики дроїдів відповідно до місця битви: \n");
        modifyDroid(droid1);
        modifyDroid(droid2);

        battleLogBuffer.append("Бій між ").append(droid1.getName()).append(" та ").append(droid2.getName())
                .append(" на арені ").append(arena.getName()).append("\n");
        if (loggingEnabled && logFilePath != null) {
            logger = new RecordBattle(logFilePath); // Ініціалізація логера
        }
    }

    // Метод для запису логів у файл
    public void writeLogToFile() throws IOException {
        if (loggingEnabled && logger != null) {
            try {
                logger.log(battleLogBuffer.toString()); // Записуємо лог до файлу
                System.out.println("Лог бою записано у файл: " + logger.toString());
            } catch (IOException e) {
                System.out.println("Помилка при записі логів у файл: " + e.getMessage());
            }
        }
    }

    // Метод для модифікації здоров'я та ушкоджень дроїдів
    private void modifyDroid(Droid droid) {
        droid.setDamage(arena.modifyDamage(droid.getDamage()));
        droid.setHealth(arena.modifyHealth(droid.getHealth()));
        logAndPrint(droid.getName() + ": Здоров'я = " + droid.getHealth() + ", Ушкодження = " + droid.getDamage());
    }

    // Метод для запуску бою
    public void startBattle() {

        logAndPrint("\nБій між " + droid1.getName() + " та " + droid2.getName() +
                " розпочато на арені: " + arena.getName() + "\n");

        while (droid1.isAlive() && droid2.isAlive()) {
            // Атака першого дроїда
            attack(droid1, droid2);
            if (!droid2.isAlive()) {
                logAndPrint(droid2.getName() + " зазнала поразки :( \n");
                break;
            }

            // Атака другого дроїда
            attack(droid2, droid1);
            if (!droid1.isAlive()) {
                logAndPrint(droid1.getName() + " зазнала поразки :( \n");
                break;
            }
        }

        // Оголошення переможця
        String winner = droid1.isAlive() ? droid1.getName() : droid2.getName();
        logAndPrint(winner + " виграла бій :)\n");
    }

    // Метод для атаки одного дроїда на іншого
    private void attack(Droid attacker, Droid defender) {
        String attackMessage = attacker.getName() + " атакує " + defender.getName() + " із рівнем ушкодження " + attacker.getDamage();
        defender.takeDamage(attacker.getDamage());
        String healthMessage = "Рівень здоров'я для " + defender.getName() + ": " + defender.getHealth() + "\n";

        // Виведення в консоль та запис у лог
        logAndPrint(attackMessage);
        logAndPrint(healthMessage);
    }

    // Метод для виведення в консоль і запису в лог
    private void logAndPrint(String message) {
        System.out.println(message); // Виведення в консоль
        battleLogBuffer.append(message).append("\n"); // Запис у буфер
        if (loggingEnabled && logger != null) { // Якщо логування увімкнено, записуємо до файлу
            try {
                logger.log(message);
            } catch (IOException e) {
                System.out.println("Помилка при записі логів у файл: " + e.getMessage());
            }
        }
    }

    // Метод для отримання журналу бою
    public String getBattleLog() {
        return battleLogBuffer.toString();
    }

    //метод для читання логів з файлу
    public static void readBattleLog(String logFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            // Читаємо кожен рядок з файлу і виводимо на консоль
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }

    // Метод для закриття логера
    public void closeLogger() {
        if (logger != null) {
            try {
                logger.close();
            } catch (IOException e) {
                System.out.println("Помилка закриття логера: " + e.getMessage());
            }
        }
    }
}
