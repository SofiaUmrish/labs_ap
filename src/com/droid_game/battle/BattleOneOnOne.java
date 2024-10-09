package com.droid_game.battle;

import com.droid_game.battle.arena.Arena;
import com.droid_game.droids.Droid;

public class BattleOneOnOne {

    private Droid droid1;
    private Droid droid2;
    private Arena arena;

    // Конструктор для ініціалізації дроїдів
    public BattleOneOnOne(Droid droid1, Droid droid2, Arena arena) {
        this.droid1 = droid1;
        this.droid2 = droid2;
        this.arena = arena;

        System.out.println("\nЗмінені характеристики дроїдів відповідно до місця битви: \n");
        modifyDroid_1_ON_1(droid1);
        modifyDroid_1_ON_1(droid2);
    }

    // Метод для модифікації здоров'я та ушкоджень дроїдів
    private void modifyDroid_1_ON_1(Droid droid) {

            droid.setDamage(arena.modifyDamage(droid.getDamage()));
            droid.setHealth(arena.modifyHealth(droid.getHealth()));

            System.out.println(droid.getName() + ": Здоров'я = " + droid.getHealth() + ", Ушкодження = " + droid.getDamage());

    }

    // Метод для запуску бою
    public void startBattle() {
        System.out.println("\nБій між " + droid1.getName() + " та " + droid2.getName() +
                " розпочато на арені: "+arena.getName() + "\n");

        while (droid1.isAlive() && droid2.isAlive()) {
            // атака 1 дроїда
            attack(droid1, droid2);
            if (!droid2.isAlive()) {
                System.out.println(droid2.getName() + " зазнала поразки :( \n");
                break;
            }

            // атака 2 дроїда
            attack(droid2, droid1);
            if (!droid1.isAlive()) {
                System.out.println(droid1.getName() + " зазнала поразки :( \n");
                break;
            }
        }

        // Оголошення переможця
        if (droid1.isAlive()) {
            System.out.println(droid1.getName() + " виграла бій :)\n");
        } else {
            System.out.println(droid2.getName() + " виграла бій :)\n");
        }
    }

    // Метод для атаки одного дроїда на іншого
    private void attack(Droid attacker, Droid defender) {
        System.out.println(attacker.getName() + " атакує " + defender.getName() + " із рівнем ушкодження  " + attacker.getDamage());
        defender.takeDamage(attacker.getDamage());
        System.out.println(" Рівень здоров'я для "+ defender.getName() +" : "+ defender.getHealth()+"\n");
    }
}
