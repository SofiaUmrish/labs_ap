package com.droid_game.battle;

import com.droid_game.battle.arena.Arena;
import com.droid_game.droids.Droid;
import com.droid_game.record_battle.RecordBattle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TeamBattle {
    private Droid[] team1;
    private Droid[] team2;
    private Arena arena;
    private RecordBattle logger; // Об'єкт для логування
    private StringBuilder battleLogBuffer; // Буфер для логів
    private boolean loggingEnabled; // Чи увімкнено логування

    // Конструктор
    public TeamBattle(Droid[] team1, Droid[] team2, Arena arena, boolean loggingEnabled) throws IOException {
        this.team1 = team1;
        this.team2 = team2;
        this.arena = arena;
        System.out.println("\nЗмінені характеристики дроїдів відповідно до місця битви: ");
        modifyDroid_team(team1);
        modifyDroid_team(team2);

        this.loggingEnabled = loggingEnabled;//прапорець
        battleLogBuffer = new StringBuilder();
        battleLogBuffer.append("Командний бій між командами:\n");
        appendTeamToLog("Команда 1", team1);
        appendTeamToLog("Команда 2", team2);
        battleLogBuffer.append("На арені ").append(arena.getName()).append("\n");
    }

    private void appendTeamToLog(String teamName, Droid[] team) {
        battleLogBuffer.append(teamName).append(": ");
        for (int i = 0; i < team.length; i++) {
            battleLogBuffer.append(team[i].getName());
            if (i < team.length - 1) {
                battleLogBuffer.append(", ");
            }
        }
        battleLogBuffer.append("\n");
    }

    // Метод для модифікації здоров'я та ушкоджень дроїдів
    private void modifyDroid_team(Droid[] team) {
        for (int i = 0; i < team.length; i++) {
            Droid droid = team[i];
            droid.setDamage(arena.modifyDamage(droid.getDamage()));
            droid.setHealth(arena.modifyHealth(droid.getHealth()));

            System.out.println(droid.getName() + ": Здоров'я = " + droid.getHealth() + ", Ушкодження = " + droid.getDamage());
        }
    }

    // Метод для запуску командного бою
    public void startTeamBattle() {
        logAndPrint("\nКомандний бій розпочато на арені: " + arena.getName() + "\n");

        // Приклад простої логіки бою
        while (areTeamsAlive()) {
            for (Droid attacker : team1) {
                Droid defender = selectRandomDefender(team2);
                if (defender != null) {
                    attack(attacker, defender);
                    if (!defender.isAlive()) {
                        logAndPrint(defender.getName() + " з команди 2 зазнала поразки.\n");
                        break;
                    }
                }
            }

            for (Droid attacker : team2) {
                Droid defender = selectRandomDefender(team1);
                if (defender != null) {
                    attack(attacker, defender);
                    if (!defender.isAlive()) {
                        logAndPrint(defender.getName() + " з команди 1 зазнала поразки.\n");
                        break;
                    }
                }
            }
        }

        // Оголошення переможця
        if (areTeamAlive(team1)) {
            logAndPrint("Команда 1 виграла бій :)\n");
        } else {
            logAndPrint("Команда 2 виграла бій :)\n");
        }
    }

    private boolean areTeamsAlive() {
        return areTeamAlive(team1) && areTeamAlive(team2);
    }

    private boolean areTeamAlive(Droid[] team) {
        for (Droid droid : team) {
            if (droid.isAlive()) {
                return true;
            }
        }
        return false;
    }

    private Droid selectRandomDefender(Droid[] team) {
        // Простий вибір першого живого дроїда
        for (Droid droid : team) {
            if (droid.isAlive()) {
                return droid;
            }
        }
        return null;
    }

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

    // Статичний метод для читання логів з файлу
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
