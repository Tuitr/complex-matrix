import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in).useLocale(Locale.US);

    //ввод матрицы с консоли
    static ComplexMatrix readMatrix(String name) {
        System.out.println("Матрица " + name + ": введите число строк и столбцов");
        int r = sc.nextInt();
        int c = sc.nextInt();
        ComplexMatrix m = new ComplexMatrix(r, c);
        System.out.println("Вводите элементы построчно: действительная и мнимая часть через пробел");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                m.setValue(i, j, new ComplexNumber(sc.nextDouble(), sc.nextDouble()));
            }
        }
        return m;
    }

    public static void main(String[] args) {
        ComplexMatrix a = readMatrix("A");
        ComplexMatrix b = readMatrix("B");
        while (true) {
            System.out.println("\n1 - A+B, 2 - A-B, 3 - A*B, 4 - A/B, 5 - транспонировать A, "
                    + "6 - определитель A, 7 - ввести матрицы заново, 0 - выход");
            int cmd = sc.nextInt();
            if (cmd == 0) {
                break;
            }
            try {
                switch (cmd) {
                    case 1 -> System.out.println(a.additionm(b));
                    case 2 -> System.out.println(a.subtractm(b));
                    case 3 -> System.out.println(a.multiply(b));
                    case 4 -> System.out.println(a.divide(b));
                    case 5 -> System.out.println(a.transposition());
                    case 6 -> System.out.println(a.determinant());
                    case 7 -> {
                        a = readMatrix("A");
                        b = readMatrix("B");
                    }
                    default -> System.out.println("Неизвестная команда");
                }
            } catch (RuntimeException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}

