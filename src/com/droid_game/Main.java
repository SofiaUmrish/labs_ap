package com.droid_game;

import com.droid_game.battle.arena.Arena;
import com.droid_game.battle.BattleOneOnOne;
import com.droid_game.battle.TeamBattle;
import com.droid_game.droids.Droid;

import java.util.Scanner;

public class Main {
    private static Droid[] droids = new Droid[6]; // Максимум 6 дроїдів
    private static int droidCount = 0; // Лічильник для дроїдів
    private static Arena arena = null; // Поле для збереження вибраної арени

    public static void menu() {
        System.out.println("\n1 - Вибір дроїда;");
        System.out.println("2 - Показати список вибраних дроїдів;");
        System.out.println("3 - Бій 1 на 1;");
        System.out.println("4 - Командний бій;");
        System.out.println("5 - Вибрати місце битви;");
        System.out.println("6 - Запис бою в файл;");
        System.out.println("7 - Відтворення бою;");
        System.out.println("8 - Закінчити гру;");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            menu();
            System.out.println("Введіть цифру: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: {
                    int number;
                    do {
                        System.out.println("Введіть кількість дроїдів (максимум 6) для створення (існує 6 видів дроїдів): ");
                        number = scanner.nextInt();
                    } while (number <= 0 || number > 6);

                    int i = 0;
                    while (i < number) {
                        if (droidCount < droids.length) {
                            droids[droidCount] = Droid.selectDroid();
                            droidCount++;
                        } else {
                            System.out.println("Досягнуто максимальної кількості дроїдів.");
                        }
                        i++;
                    }
                    break;
                }
                case 2: {
                    System.out.println("Список вибраних дроїдів:");
                    for (int i = 0; i < droidCount; i++) {
                        System.out.println((i + 1) + ". " + droids[i]);
                    }
                    break;
                }
                case 3: {
                    if (droidCount < 2) {
                        System.out.println("Потрібно хоча б два дроїда для бою 1 на 1.");
                    } else {
                        System.out.println("Виберіть дроїдів для бою 1 на 1 зі списку створених.\n");

                        for (int i = 0; i < droidCount; i++) {
                            System.out.println((i + 1) + ". " + droids[i]);
                        }

                        System.out.println("\nВведіть номер першого дроїда(він буде атакувати першим): ");
                        int droid1Numb = scanner.nextInt();
                        droid1Numb -= 1;
                        System.out.println("Введіть номер другого дроїда: ");
                        int droid2Numb = scanner.nextInt();
                        droid2Numb -= 1;

                        if (droid1Numb >= 0 && droid1Numb < droidCount &&
                                droid2Numb >= 0 && droid2Numb < droidCount && droid1Numb != droid2Numb) {

                            Droid droid1 = droids[droid1Numb];
                            Droid droid2 = droids[droid2Numb];

                            // Запуск бою з обраною ареною
                            if (arena == null) {
                                System.out.println("Не вибрано місце битви. Буде використано арену за замовчуванням.");
                                arena = new Arena.Forest(); // Вибір арени за замовчуванням
                            }

                            BattleOneOnOne battle = new BattleOneOnOne(droid1, droid2, arena);
                            battle.startBattle();
                        } else {
                            System.out.println("Невірний вибір дроїдів. Спробуйте знову.");
                        }
                    }
                    break;
                }
                case 4: {
                    if (droidCount < 4) {
                        System.out.println("Потрібно хоча б чотири дроїда для командного бою.");

                    } else {
                        int number = 0;
                        do {
                            System.out.println("Введіть кількість дроїдів для командного бою (4, якщо бій 2*2 або 6, якщо бій 3*3):");
                            number = scanner.nextInt();
                        } while (number != 4 && number != 6||(number == 6&&droidCount<6));

                        Droid[] team1 = new Droid[number]; // Команда 1
                        Droid[] team2 = new Droid[number]; // Команда 2

                        System.out.println("Виберіть дроїдів для команди 1:");
                        for (int i = 0; i < droidCount; i++) {
                            System.out.println((i + 1) + ". " + droids[i]);
                        }

                        for (int i = 0; i < team1.length; i++) {
                            System.out.println("Введіть номер дроїда для команди 1:");
                            int droidIndex = scanner.nextInt();
                            team1[i] = droids[droidIndex - 1];
                        }

                        System.out.println("Виберіть дроїдів для команди 2:");
                        for (int i = 0; i < team2.length; i++) {
                            System.out.println("Введіть номер дроїда для команди 2:");
                            int droidIndex = scanner.nextInt();
                            team2[i] = droids[droidIndex - 1];
                        }

                        // Запуск командного бою з обраною ареною
                        if (arena == null) {
                            System.out.println("Не вибрано місце битви. Буде використано арену за замовчуванням.");
                            arena = new Arena.Forest(); // Вибір арени за замовчуванням
                        }

                        TeamBattle teamBattle = new TeamBattle(team1, team2, arena);
                        teamBattle.startTeamBattle();
                    }
                    break;
                }
                case 5: {
                    arena = Arena.selectArena();
                    break;
                }
                case 6: {

                    break;
                }
                case 7: {

                    break;
                }
                case 8: {
                    System.out.println("Кінець гри.");
                    break;
                }
                default: {
                    System.out.println("Неправильний вибір. Спробуйте ще раз.");
                }
            }
        } while (choice != 8);

        scanner.close();
    }
}
