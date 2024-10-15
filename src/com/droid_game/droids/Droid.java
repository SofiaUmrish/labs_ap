package com.droid_game.droids;

import java.util.Scanner;

public abstract class Droid {
    private String name;
    private int damage;
    private int health;
    private String color;

    public Droid(String name, int damage, int health, String color) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public String getColor() {
        return color;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    @Override
    public String toString() {
        return "Дроїд {" +
                "Ім'я: " + name  +
                ", Здоров'я: " + health +
                ", Ушкодження: " + damage +
                ", Колір: " + color +
                '}';
    }

    public static class BloomDroid extends Droid {
        public BloomDroid() {
            super("Bloom", 16, 180, "orange");
        }
    }

    public static class StellaDroid extends Droid {
        public StellaDroid() {
            super("Stella", 32, 96, "yellow");
        }
    }

    public static class MuseDroid extends Droid {
        public MuseDroid() {
            super("Muse", 10, 200, "purple");
        }
    }

    public static class FloraDroid extends Droid {
        public FloraDroid() {
            super("Flora", 38, 99, "green");
        }
    }

    public static class LeylaDroid extends Droid {
        public LeylaDroid() {
            super("Leyla", 14, 188, "pink");
        }
    }

    public static class DarcyDroid extends Droid {
        public DarcyDroid() {
            super("Darcy", 42, 88, "blue");
        }
    }

    public static Droid selectDroid() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Виберіть дроїда зі списку:");
        System.out.println("1. BloomDroid");
        System.out.println("2. StellaDroid");
        System.out.println("3. MuseDroid");
        System.out.println("4. FloraDroid");
        System.out.println("5. LeylaDroid");
        System.out.println("6. DarcyDroid");



        System.out.print("Введіть номер дроїда: ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                return new BloomDroid();
            }
            case 2: {
                return new StellaDroid();
            }
            case 3: {
                return new MuseDroid();
            }
            case 4: {
                return new FloraDroid();
            }
            case 5: {
                return new LeylaDroid();
            }
            case 6: {
                return new DarcyDroid();
            }
            default:
                System.out.println("Невірний вибір. Обрано BloomDroid за замовчуванням.");
                return new BloomDroid();
        }
    }
}
