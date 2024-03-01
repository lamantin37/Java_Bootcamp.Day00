package day00.ex01;


import java.util.Scanner;

public class Program {

    private static void checkNumValid(int num) {
        if (num <= 0 || num == 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
    }

    private static void checkPrime(int number) {
        int iter = 1;
        boolean isPrime = number > 1 && (number == 2 || number % 2 != 0) && (number == 3 || number % 3 != 0);
        if (isPrime) {
            for (int i = 6; i * i <= number; i += 6, ++iter) {
                if (number % (i - 1) == 0 || number % (i + 1) == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        System.out.println(isPrime + " " + iter);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        checkNumValid(num);
        checkPrime(num);

        scanner.close();
    }
}
