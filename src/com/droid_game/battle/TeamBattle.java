package com.droid_game.battle;

import com.droid_game.battle.arena.Arena;
import com.droid_game.droids.Droid;

public class TeamBattle {
    private Droid[] droid_team1;
    private Droid[] droid_team2;
    private Arena arena;

    // Конструктор для ініціалізації дроїдів
    public TeamBattle(Droid[] droid_team1, Droid[] droid_team2, Arena arena) {
        this.droid_team1 = droid_team1;
        this.droid_team2 = droid_team2;
        this.arena = arena;

        // Модифікація ушкодження та здоров'я дроїдів один раз
        modifyDroid_team(droid_team1);
        modifyDroid_team(droid_team2);
    }

    // Метод для модифікації здоров'я та ушкоджень дроїдів
    private void modifyDroid_team(Droid[] team) {
        System.out.println("Змінені характеристики дроїдів відповідно до місця битви: \n");
        for (int i = 0; i < team.length; i++) {
            Droid droid = team[i];
            droid.setDamage(arena.modifyDamage(droid.getDamage()));
            droid.setHealth(arena.modifyHealth(droid.getHealth()));

            System.out.println(droid.getName() + ": Здоров'я = " + droid.getHealth() + ", Ушкодження = " + droid.getDamage());
        }
    }

    // Метод для запуску командного бою
    public void startTeamBattle() {
        System.out.println("\nКомандний бій розпочато!\n");

        // Цикл бою, поки обидві команди мають живих дроїдів
        while (hasAliveDroids(droid_team1) && hasAliveDroids(droid_team2)) {
            // Вибір дроїдів для атаки
            Droid droid1 = selectDroid(droid_team1);
            Droid droid2 = selectDroid(droid_team2);

            if (droid1 == null || droid2 == null) {
                break; // Якщо немає живих дроїдів, виходимо з бою
            }

            // Атака першого дроїда
            attack(droid1, droid2);
            if (!droid2.isAlive()) {
                System.out.println(droid2.getName() + " зазнав поразки :(\n");
                continue; // Продовжити, якщо другий дроїд знищений
            }

            // Атака другого дроїда, якщо він ще живий
            if (hasAliveDroids(droid_team2)) {
                attack(droid2, droid1);
                if (!droid1.isAlive()) {
                    System.out.println(droid1.getName() + " зазнав поразки :(\n");
                }
            }
        }

        // Оголошення переможця
        if (hasAliveDroids(droid_team1)) {
            System.out.println("Команда 1 виграла бій :)\n");
        } else {
            System.out.println("Команда 2 виграла бій :)\n");
        }
    }
    // Метод для вибору живого дроїда з команди (з найменшим здоров'ям)
    private Droid selectDroid(Droid[] team) {
        Droid droidWithLowestHealth = null;

        for (int i = 0; i < team.length; i++) {
            Droid droid = team[i];
            if (droid.isAlive()) {
                if (droidWithLowestHealth == null || droid.getHealth() < droidWithLowestHealth.getHealth()) {
                    droidWithLowestHealth = droid;
                }
            }
        }
        return droidWithLowestHealth;
    }
    // Метод для атаки одного дроїда на іншого
    private void attack(Droid attacker, Droid defender) {
        System.out.println(attacker.getName() + " атакує " + defender.getName() + " із рівнем ушкодження " + attacker.getDamage());
        defender.takeDamage(attacker.getDamage());
        System.out.println(" Рівень здоров'я для " + defender.getName() + " : " + defender.getHealth() + "\n");
    }
    // Метод для перевірки, чи є живі дроїди в команді
    private boolean hasAliveDroids(Droid[] team) {
        for (int i = 0; i < team.length; i++) {
            Droid droid = team[i];
            if (droid.isAlive()) {
                return true;
            }
        }
        return false;
    }

}
