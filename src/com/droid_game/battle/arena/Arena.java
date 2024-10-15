package com.droid_game.battle.arena;

import java.util.Scanner;

public class Arena {
    private String name;
    private int damageModifier;
    private int healthModifier;


    public Arena(String name, int damageModifier, int healthModifier) {
        this.name = name;
        this.damageModifier = damageModifier;
        this.healthModifier = healthModifier;
    }

    public String getName() {
        return name;
    }
    public int getDamageModifier() {
        return damageModifier;
    }
    public int getHealthModifier() {
        return healthModifier;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }
    public void setHealthModifier(int healthModifier) {
        this.healthModifier = healthModifier;
    }

    @Override
    public String toString() {
        return "Місце битви {" +
                "Назва: " + name +
                ", Модифікатор ушкодження: " + damageModifier +
                ", Модифікатор здоров'я: " + healthModifier +
                '}';
    }

    // Метод для отримання модифікованої атаки з урахуванням арени
    public int modifyDamage(int baseDamage) {
        return baseDamage + damageModifier;
    }

    // Метод для отримання модифікованого захисту з урахуванням арени
    public int modifyHealth(int baseHealth) {
        return baseHealth + healthModifier;
    }


    // ліс,  водоспад, пустеля, лабіринт

    public static class Forest extends Arena {
        public Forest() {
            super("Ліс", -4, 7);
        }
    }

    public static class Waterfall extends Arena {
        public Waterfall() {
            super("Водоспад", -5, 6);
        }
    }

    public static class Desert extends Arena {
        public Desert() {
            super("Пустеля", -3, -10);
        }
    }

    public static class Labyrinth extends Arena {
        public Labyrinth() {
            super("Лабіринт", 3, 8);
        }
    }
    public static Arena selectArena() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Виберіть бажане місце битви: ");
        System.out.println("1. Ліс");
        System.out.println("2. Водоспад");
        System.out.println("3. Пустеля");
        System.out.println("4. Лабіринт");
        System.out.println("5.Отримати додаткову інформацію про місця битви.");


        System.out.print("Введіть номер арени або опцію отримання додаткової інформації: ");
        int choice = scanner.nextInt();
        if(choice == 5)
        {
            System.out.println("\n\t\tДОДАТКОВА ІНФОРМАЦІЯ\n ");
            System.out.println("1. Арена 'Ліс' зменшує значення damage на 4 та збільшує health на 7.\n Свіже повітря та " +
                    "натуральні умови лісу сприяють відновленню сил дроїдів, покращуючи\n їх здоров'я " +
                    "завдяки цілющим властивостям навколишніх рослин.Однак,\n чисельні природні перешкоди" +
                    " ускладнюють точність ударів, зменшуючи ушкодження.\n");

            System.out.println("2. Арена 'Водоспад' збільшує значення damage на 5 та зменшує health на 6.\n" +
                    " Водоспад - це сильне джерело енергії, що активізує дроїдів, збільшуючи\n їх силу ушкодження. " +
                    "Проте,вологе середовище  впливає на здоров'я,\n знижуючи його рівень.\n");

            System.out.println("3. Арена 'Пустеля' зменшує значення damage на 3 та зменшує health на 10.\n Спекотні та " +
                    "сухі умови виснажують дроїдів. Відсутність води та ресурсів\n негативно впливають на їх здоров'я." +
                    " У той же час, піщані бурі та місцями\n сипучі піски заважають дроїдам завдавати ударів " +
                    "з максимальною силою.\n");


            System.out.println("4. Арена 'Лабіринт' збільшує значення damage на 3 та збільшує health на 8.\n Лабіринт, " +
                    "завдяки своїм численним поворотам і укриттям, дає змогу дроїдам сховатися\n і спланувати свої атаки, " +
                    "що підвищує їхню здатність завдавати ударів. "+ "Окрім того,\n відсутність впливу зовнішніх чинників " +
                    "на хід бою, дає можливість відновити сили, \nпокращуючи їх здоров'я.\n");

            choice=0;
            System.out.print("Введіть номер арени: ");
            int choice_2 = scanner.nextInt();
            choice=choice_2;
        }

        switch (choice) {
            case 1: {
                System.out.println("\n Обрано місце бою 'Ліс'.\n");
                return new Arena.Forest();
            }
            case 2: {
                System.out.println("\n Обрано місце бою 'Водоспад'.\n");
                return new Arena.Waterfall();
            }
            case 3: {
                System.out.println("\n Обрано місце бою 'Пустеля'.\n");
                return new Arena.Desert();
            }
            case 4: {
                System.out.println("\n Обрано місце бою 'Лабіринт'.\n");
                return new Arena.Labyrinth();
            }

            default:
                System.out.println("\nОбрано місце бою 'Ліс' за замовчуванням.\n");
                return new Arena.Forest();
        }

    }
}