package com.droid_game;

import com.droid_game.battle.arena.Arena;
import com.droid_game.battle.BattleOneOnOne;
import com.droid_game.battle.TeamBattle;
import com.droid_game.droids.Droid;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static Droid[] droids = new Droid[7];
    private static int droidCount = 0; // лічильник для дроїдів
    private static Arena arena = null; //  для збереження вибраної арени
    private static Droid droid1;
    private static Droid droid2;
    private static String lastBattleLog = ""; // для збереження логу останнього бою

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
        String logFilePath;
        do {
            menu();
           // System.out.print("Введіть цифру: ");

            do {
                System.out.print("Введіть цифру: ");
                choice = scanner.nextInt();

            }while(choice<=0||choice>8);

            switch (choice) {
                case 1: {
                    int number;
                        do {
                            System.out.print("Введіть кількість дроїдів (максимум 6) для створення (існує 6 видів дроїдів): ");
                            number = scanner.nextInt();

                        }while(number<=0||number>6);


                    for (int i = 0; i < number; i++) {
                        if (droidCount < droids.length) {
                            droids[droidCount] = Droid.selectDroid();
                            droidCount++;
                        } else {
                            System.out.println("Досягнуто максимальної кількості дроїдів.");
                            break;
                        }
                    }
                    break;
                }
                case 2: { // Показати список дроїдів
                    if (droidCount == 0) {
                        System.out.println("Немає вибраних дроїдів.");
                    } else {
                        System.out.println("Список вибраних дроїдів:");
                        for (int i = 0; i < droidCount; i++) {
                            System.out.println((i + 1) + ". " + droids[i]);
                        }
                    }
                    break;
                }
                case 3: { // Бій 1 на 1 без логування
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

                            if (arena == null) {
                                System.out.println("Не вибрано місце битви. Буде використано арену за замовчуванням.");
                                arena = new Arena.Forest(); // Вибір арени за замовчуванням
                            }

                            try {
                                String filePath = "D:\\battleLog"; // Вкажіть значення, якщо це потрібно
                                BattleOneOnOne battle = new BattleOneOnOne(droid1, droid2, arena, filePath, false);
                                battle.startBattle(); // Запускаємо бій
                                lastBattleLog = battle.getBattleLog(); // Зберігаємо лог бою
                                System.out.println("Бій завершено.");
                            } catch (IOException e) {
                                System.out.println("Помилка при ініціалізації бою: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Невірний вибір дроїдів. Спробуйте знову.");
                        }
                    }
                    break;
                }
                case 4: { // Командний бій
                    if (droidCount < 4) {
                        System.out.println("Потрібно хоча б чотири дроїда для командного бою.");
                    } else {
                        int number = 0;
                        do {
                            System.out.println("Введіть кількість дроїдів для командного бою (4, якщо бій 2*2 або 6, якщо бій 3*3):");
                            number = scanner.nextInt();
                        } while (number != 4 && number != 6||(number == 6&&droidCount<6));

                        Droid[] team1 = new Droid[number/2];
                        Droid[] team2 = new Droid[number/2];

                        System.out.println("Виберіть дроїдів для команди 1:");
                        for (int i = 0; i < droidCount; i++) {
                            System.out.println((i + 1) + ". " + droids[i]);
                        }

                        for (int i = 0; i < team1.length; i++) {
                            System.out.println("\nВведіть номер дроїда для команди 1:");
                            int droidIndex = scanner.nextInt();
                            team1[i] = droids[droidIndex - 1];
                        }

                        System.out.println("\nВиберіть дроїдів для команди 2:");
                        for (int i = 0; i < team2.length; i++) {
                            System.out.println("Введіть номер дроїда для команди 2:");
                            int droidIndex = scanner.nextInt();
                            team2[i] = droids[droidIndex - 1];
                        }

                        // Відображення обраних команд
                        System.out.println("\nКоманда 1:");
                        for (int i = 0; i < team1.length; i++) {
                            System.out.println((i + 1) + ". " + team1[i]);
                        }

                        System.out.println("\nКоманда 2:");
                        for (int i = 0; i < team2.length; i++) {
                            System.out.println((i + 1) + ". " + team2[i]);
                        }

                        if (arena == null) {
                            System.out.println("\nНе вибрано місце битви. Буде використано арену за замовчуванням.");
                            arena = new Arena.Forest();
                        }

                        try {
                            TeamBattle teamBattle = new TeamBattle(team1, team2, arena, false);
                            teamBattle.startTeamBattle(); // Запускаємо бій
                            lastBattleLog = teamBattle.getBattleLog(); // Зберігаємо лог бою
                            System.out.println("Командний бій завершено.");
                        } catch (IOException e) {
                            System.out.println("Помилка при ініціалізації командного бою: " + e.getMessage());
                        }
                    }
                    break;
                }
                case 5: { // Вибрати місце битви
                    arena = Arena.selectArena();
                    break;
                }
                case 6: { // Запис бою в файл
                    if (lastBattleLog.isEmpty()) {
                        System.out.println("Немає бою для запису. Спочатку проведіть бій.");
                    } else {
                        System.out.print("Введіть шлях до файлу для запису журналу бою (наприклад, battle_log.txt): ");
                        logFilePath = scanner.next();

                        try (FileWriter fw = new FileWriter(logFilePath)) {

                            fw.write(lastBattleLog);
                            System.out.println("Журнал бою успішно записано у файл: " + logFilePath);
                        } catch (IOException e) {
                            System.out.println("Помилка при записі файлу: " + e.getMessage());
                        }
                    }
                    break;
                }
                case 7: { // Відтворення бою
                    System.out.print("Введіть шлях до файлу журналу бою для відтворення: ");
                    logFilePath = scanner.next();

                    File file = new File(logFilePath);
                    if (file.exists()) {
                      if(droidCount<4){
                          BattleOneOnOne.readBattleLog(logFilePath);
                      }
                       else{
                          TeamBattle.readBattleLog(logFilePath);
                      }

                    } else {
                        System.out.println("Файл журналу бою не знайдено. Спробуйте ще раз.");
                    }
                    break;
                }
                case 8: { // Закінчити гру
                    System.out.println("Кінець гри.");
                    break;
                }
                default: { // Неправильний вибір
                    System.out.println("Неправильний вибір. Спробуйте ще раз.");
                }
            }
        } while (choice != 8);

        scanner.close();
    }
}

