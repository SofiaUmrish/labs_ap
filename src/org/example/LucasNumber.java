package org.example;
/**
 * Клас для обчислення числа Люка за заданим номером.
 */
public class LucasNumber {
    private int n; // Номер числа Люка
    private int value; // Значення числа Люка

    /**
     * Конструктор класу LucasNumber.
     * @param n номер числа Люка.
     */
    public LucasNumber(int n) {
        this.n = n;
        this.value = calculateLucas(n); // Обчислюємо число Люка
    }

    /**
     * Обчислює число Люка за номером.
     * @param n номер числа Люка.
     * @return значення числа Люка.
     */
    public int calculateLucas(int n) {
        if (n == 1) return 1; // L1 = 1
        if (n == 2) return 3; // L2 = 3
        int a = 1, b = 3, c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * Повертає значення числа Люка.
     * @return значення числа Люка.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Повертає номер числа Люка.
     * @return номер числа Люка.
     */
    public int getNumber() {
        return this.n;
    }
}
