package org.example;
import java.util.Scanner;

/**
 * Головний клас для обчислення та виведення чисел Люка і суми їх квадратів.
 */
public class Main {

    /**
     * Обчислює суму квадратів перших N чисел Люка.
     * @param N кількість чисел Люка.
     * @return сума квадратів перших N чисел Люка.
     */
    public static int sumOfSquaresLucas(int N) {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            LucasNumber lucasNumber = new LucasNumber(i);
            sum += Math.pow(lucasNumber.getValue(), 2); // Додаємо квадрат числа Люка
        }
        return sum;
    }

    /**
     * Головний метод програми. Вводить кількість чисел Люка,
     * обчислює їх, виводить значення і обчислює суму квадратів.
     * @param args аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Введення кількості чисел Люка
        System.out.print("Введіть кількість перших чисел Люка (N): ");
        int N = scanner.nextInt();

        // Виведення перших N чисел Люка та їх квадратів
        System.out.println("Перші " + N + " чисел Люка:");
        for (int i = 1; i <= N; i++) {
            LucasNumber lucasNumber = new LucasNumber(i);
            System.out.println("L" + i + " = " + lucasNumber.getValue());
        }

        // Обчислення суми квадратів
        int sumSquares = sumOfSquaresLucas(N);
        System.out.println("Сума квадратів перших " + N + " чисел Люка: " + sumSquares);
    }
}
